package com.mental_calc.gamification.game;

import java.util.List;

import com.mental_calc.gamification.challenge.ChallengeSolvedEvent;
import com.mental_calc.gamification.game.domain.BadgeType;

import lombok.Value;

public interface GameService {

	/**
	 * recalculate badges and scores after a new attempt
	 * @param challenge
	 * @return
	 */
	GameResult newAttempt(ChallengeSolvedEvent challenge);
	
	@Value
	class GameResult {
		int score;
		List<BadgeType> badges;
	}
}
