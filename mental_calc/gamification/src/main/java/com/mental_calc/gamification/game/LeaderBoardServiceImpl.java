package com.mental_calc.gamification.game;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mental_calc.gamification.game.domain.LeaderBoard;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaderBoardServiceImpl implements LeaderBoardService {

	private final ScoreRepository scoreRepository;
	private final BadgeRepository badgeRepository;
	
	@Override
	public List<LeaderBoard> getLeaderBoard() {
		//1. get score
		List<LeaderBoard> scoreLeads = scoreRepository.listFirst10();
		
		//2. score and badge
		return scoreLeads.stream().map(row -> {
			List<String> badges = badgeRepository.findByUserIdOrderByBadgeTimestampDesc(
					row.getUserId())
					.stream()
					.map(b -> b.getBadgeType().getDescription())
					.collect(Collectors.toList());
			return row.withBadges(badges);
		}).collect(Collectors.toList());
	}

}
