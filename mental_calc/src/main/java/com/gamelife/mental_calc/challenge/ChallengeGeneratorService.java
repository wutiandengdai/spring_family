package com.gamelife.mental_calc.challenge;

public interface ChallengeGeneratorService {

	/**
	 * Generates a random challenge with factors between 11 and 99
	 * @return
	 */
	Challenge generateChallenge();
}
