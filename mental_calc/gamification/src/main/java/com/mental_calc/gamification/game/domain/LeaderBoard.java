package com.mental_calc.gamification.game.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.With;


@Value
@AllArgsConstructor
public class LeaderBoard {

	Long userId;
	Long totalScore;
	
	//generates a method "wdithBadge(badges)s to clone a value and add new field
	@With
	List<String> badges;
	
	public LeaderBoard(final Long userId, final Long totalScore) {
		this.userId = userId;
		this.totalScore = totalScore;
		//initialize with empty string if parameter is not given
		this.badges = List.of(); 
	}
}
