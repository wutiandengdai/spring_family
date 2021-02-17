package com.mental_calc.multiplication.challenge;

public interface ChallengeGeneratorService {

	/**
	 * Generates a random challenge with factors between 11 and 99
	 * @return
	 */
	Challenge generateChallenge();
}
