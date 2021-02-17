package com.mental_calc.multiplication.challenge;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mental_calc.multiplication.user.User;
import com.mental_calc.multiplication.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChallengeServiceImpl implements ChallengeService {

	private final UserRepository userRepository;
	private final ChallengeAttemptRepository attemptRepository;
	
	@Override
	public ChallengeAttempt testAttempt(ChallengeAttemptDTO attemptDTO) {
		boolean isCorrect = attemptDTO.getGuess() == attemptDTO.getFactorA() * attemptDTO.getFactorB();
		
		//If user does not exit, create user and save
		User user = userRepository.findByAlias(attemptDTO.getUserAlias()).orElseGet(
					() -> {
						log.info("Create new user with alias {}", attemptDTO.getUserAlias());
						return userRepository.save(new User(attemptDTO.getUserAlias()));
					}
				);
		
		ChallengeAttempt testedAttempt = new ChallengeAttempt (0l, user, attemptDTO.getFactorA(),
				attemptDTO.getFactorB(), attemptDTO.getGuess(), isCorrect);
		
		//save attempt
		attemptRepository.save(testedAttempt);
		
		return testedAttempt;
	}

	@Override
	public List<ChallengeAttempt> getRecentAttemptsByUser(String userAlias) {
		return attemptRepository.findLatest10ByUserAlias(userAlias);
	}
	

}
