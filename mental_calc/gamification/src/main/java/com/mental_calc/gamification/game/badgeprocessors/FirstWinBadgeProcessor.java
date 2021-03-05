package com.mental_calc.gamification.game.badgeprocessors;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mental_calc.gamification.challenge.ChallengeSolvedEvent;
import com.mental_calc.gamification.game.domain.BadgeType;
import com.mental_calc.gamification.game.domain.ScoreCard;

@Component
public class FirstWinBadgeProcessor implements BadgeProcessor {

	@Override
	public Optional<BadgeType> processForOptionBadge(int currentScore, List<ScoreCard> scoreCardList,
			ChallengeSolvedEvent solved) {
		return scoreCardList.size() == 1 ?
				Optional.of(badgeType()):
				Optional.empty();
	}

	@Override
	public BadgeType badgeType() {
		return BadgeType.FIRST_WIN;
	}

}
