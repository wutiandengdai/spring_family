package com.mental_calc.gamification.game.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BadgeType {
	
	//Badges depending on scores
	BRONZE("Bronze"),
	SILVER("Silver"),
	GOLD("Gold"),
	
	//other badges
	FIRST_WIN("First Win"),
	LUCKY_NUMBER("Lucky Number");
	
	private final String description;
}
