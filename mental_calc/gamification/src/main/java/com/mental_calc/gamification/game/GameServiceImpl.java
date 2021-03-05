package com.mental_calc.gamification.game;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mental_calc.gamification.challenge.ChallengeSolvedEvent;
import com.mental_calc.gamification.game.badgeprocessors.BadgeProcessor;
import com.mental_calc.gamification.game.domain.BadgeCard;
import com.mental_calc.gamification.game.domain.BadgeType;
import com.mental_calc.gamification.game.domain.ScoreCard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

	private final ScoreRepository scoreRepository;
	private final BadgeRepository badgeRepository;
	
	//Spring collects all spring beans (@Component) for @Service, @Repository, @Controller
	private final List<BadgeProcessor> badgeProcessors;
	
	/**
	 * Badges and scores will be updated in transaction
	 */
	@Transactional
	@Override
	public GameResult newAttempt(final ChallengeSolvedEvent challenge) {
		if(challenge.isCorrect() ) {
			ScoreCard scoreCard = new ScoreCard(challenge.getUserId(), challenge.getAttemptId());
			scoreRepository.save(scoreCard);
			
			log.info( "User {} scored {} points for attempt id {}", 
					challenge.getUserAlias(), scoreCard.getScore(), challenge.getAttemptId());
			
			List<BadgeCard> badgeCards = processForBadges(challenge);
			return new GameResult(scoreCard.getScore(),
					badgeCards.stream().map(BadgeCard::getBadgeType).collect(Collectors.toList()));
			
		}else {
			log.info("Attempt id {} is not correct. User {} does not score.", challenge.getAttemptId(), challenge.getUserAlias());
			return new GameResult(0, List.of());
		}
	}
	
	private List<BadgeCard> processForBadges( final ChallengeSolvedEvent solvedChallenge){
		Optional<Integer> optTotalScore = scoreRepository.getTotalScoreForUser(solvedChallenge.getUserId());
		if (optTotalScore.isEmpty()) {
			return Collections.emptyList();
		}
		
		int totalScore = optTotalScore.get();
		List<ScoreCard> scoreCardList = scoreRepository.findByUserIdOrderByScoreTimestampDesc(solvedChallenge.getUserId());
		Set<BadgeType> alreadyGotBadges = badgeRepository.findByUserIdOrderByBadgeTimestampDesc(solvedChallenge.getUserId())
				.stream().map(BadgeCard::getBadgeType).collect(Collectors.toSet());
		
		//return new badge for the user
		List<BadgeCard> newBadges = badgeProcessors
				.stream()
				.filter(bp->!alreadyGotBadges.contains(bp.badgeType()))
				.map(bp -> bp.processForOptionBadge(totalScore, scoreCardList, solvedChallenge))
				.flatMap(Optional::stream)
				.map(badgeType -> new BadgeCard(solvedChallenge.getUserId(), badgeType))
				.collect(Collectors.toList());
				
		badgeRepository.saveAll(newBadges);
		
		return newBadges;
	}

}
