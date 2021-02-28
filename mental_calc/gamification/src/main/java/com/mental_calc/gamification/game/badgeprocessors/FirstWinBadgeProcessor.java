package com.mental_calc.gamification.game.badgeprocessors;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mental_calc.gamification.challenge.ChallengeSolvedDTO;
import com.mental_calc.gamification.game.domain.BadgeType;
import com.mental_calc.gamification.game.domain.ScoreCard;

@Component
public class FirstWinBadgeProcessor implements BadgeProcessor {

	@Override
	public Optional<BadgeType> processForOptionBadge(int currentScore, List<ScoreCard> scoreCardList,
			ChallengeSolvedDTO solved) {
		return currentScore > 400 ?
				Optional.of(badgeType()):
				Optional.empty();
	}

	@Override
	public BadgeType badgeType() {
		return BadgeType.GOLD;
	}

}
