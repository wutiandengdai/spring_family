package com.mental_calc.multiplication.challenge;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ChallengeAttemptRepository extends CrudRepository<ChallengeAttempt, Long>{

	/**
	 * ?1 - refer to the first parameter
	 * ! LIMIT is not supported in h2 v1.4
	 * @param userAlias
	 * @return
	 */
	@Query("SELECT c FROM ChallengeAttempt c WHERE c.user.alias=?1 ORDER BY c.id DESC")
	List<ChallengeAttempt> findLatest10ByUserAlias(String userAlias);
}
