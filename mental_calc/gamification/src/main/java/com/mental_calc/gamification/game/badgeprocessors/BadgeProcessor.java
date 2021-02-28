package com.mental_calc.gamification.game.badgeprocessors;

import java.util.List;
import java.util.Optional;

import com.mental_calc.gamification.challenge.ChallengeSolvedDTO;
import com.mental_calc.gamification.game.domain.BadgeType;
import com.mental_calc.gamification.game.domain.ScoreCard;

public interface BadgeProcessor {

	/**
	 * Check if current solved challenge qualifies the user of a badge
	 * @param curentScore
	 * @param scoreCardList
	 * @param solved
	 * @return
	 */
	Optional<BadgeType> processForOptionBadge(
			int currentScore,
			List<ScoreCard> scoreCardList,
			ChallengeSolvedDTO solved);
	
	BadgeType badgeType();
}
