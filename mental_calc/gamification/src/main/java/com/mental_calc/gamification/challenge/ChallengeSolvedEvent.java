package com.mental_calc.gamification.challenge;

import lombok.Value;

/**
 * Object that carries the data between processes,for example when you are working with a remote interface
 * @author lei
 *
 */
@Value
public class ChallengeSolvedEvent {

	long attemptId;
	boolean correct;
	int factorA;
	int factorB;
	long userId;
	String userAlias;
}
