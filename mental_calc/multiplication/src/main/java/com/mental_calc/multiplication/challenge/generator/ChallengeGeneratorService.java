package com.mental_calc.multiplication.challenge.generator;

import com.mental_calc.multiplication.challenge.Challenge;

public interface ChallengeGeneratorService {

	/**
	 * Generates a random challenge with factors between 11 and 99
	 * @return
	 */
	Challenge generateChallenge();
}
