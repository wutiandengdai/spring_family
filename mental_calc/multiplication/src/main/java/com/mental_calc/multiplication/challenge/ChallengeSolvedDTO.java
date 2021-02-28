package com.mental_calc.multiplication.challenge;

import lombok.Value;

/**
 * API Object , for Gamification service
 * @author lei
 *
 */
@Value
public class ChallengeSolvedDTO {

	long attemptId;
	boolean correct;
	int factorA;
	int factorB;
	long userId;
	String userAlias;
}
