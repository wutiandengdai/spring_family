package com.mental_calc.gamification.game.badgeprocessors;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mental_calc.gamification.challenge.ChallengeSolvedDTO;
import com.mental_calc.gamification.game.domain.BadgeType;
import com.mental_calc.gamification.game.domain.ScoreCard;

/*
 * Spring bean
 */
@Component
public class LuckyBadgeProcessor implements BadgeProcessor {

	private final int LUCKY_NUMBER = 42;
	
	@Override
	public Optional<BadgeType> processForOptionBadge(int currentScore, List<ScoreCard> scoreCardList,
			ChallengeSolvedDTO solved) {
		return List.of(solved.getFactorA(), solved.getFactorB()).contains(LUCKY_NUMBER) ?
				Optional.of(badgeType()) :
				Optional.empty();
	}

	@Override
	public BadgeType badgeType() {
		return BadgeType.LUCKY_NUMBER;
	}

}
