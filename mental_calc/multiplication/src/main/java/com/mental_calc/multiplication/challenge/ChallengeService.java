package com.mental_calc.multiplication.challenge;

import java.util.List;

public interface ChallengeService {

	/**
	 * Test if attempt is correct
	 * @param resultAttempt
	 * @return
	 */
	ChallengeAttempt testAttempt (ChallengeAttemptDTO attemptDTO);
	
	List<ChallengeAttempt> getRecentAttemptsByUser(String userAlias);
}
