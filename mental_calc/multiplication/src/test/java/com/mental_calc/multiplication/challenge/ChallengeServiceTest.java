package com.mental_calc.multiplication.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mental_calc.multiplication.challenge.ChallengeAttempt;
import com.mental_calc.multiplication.challenge.ChallengeAttemptDTO;
import com.mental_calc.multiplication.challenge.ChallengeAttemptRepository;
import com.mental_calc.multiplication.challenge.ChallengeService;
import com.mental_calc.multiplication.challenge.ChallengeServiceImpl;
import com.mental_calc.multiplication.user.User;
import com.mental_calc.multiplication.user.UserRepository;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ChallengeServiceTest {

	private ChallengeService challengeService;
	
	@Mock
	private UserRepository userRepository;
	@Mock
	private ChallengeAttemptRepository attemptRepository;
	
	@BeforeEach
	public void setUp() {
		challengeService = new ChallengeServiceImpl(userRepository, attemptRepository);
		
		//Instead of carry out the real save action, returns only the first argument from input
		given(attemptRepository.save(any())).will(returnsFirstArg());
	}
	
	//Test true condition
	@Test
	public void checkCorrectAttemptTest() {
		//given 
		ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO (50, 60, "john_doe", 3000);
		//when
		ChallengeAttempt resultAttempt = challengeService.testAttempt(attemptDTO);
		//then
		then(resultAttempt.isCorrect()).isTrue();
		verify(userRepository).save(new User("john_doe"));
		verify(attemptRepository).save(resultAttempt);
	}
	//Test false condition
	@Test
	public void checkWrongAttemptTest() {
		//given
		ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO (50, 60, "john_doe", 5000);
		//when
		ChallengeAttempt resultAttempt = challengeService.testAttempt(attemptDTO);
		//then
		then(resultAttempt.isCorrect()).isFalse();
		verify(userRepository).save(new User("john_doe"));
		verify(attemptRepository).save(resultAttempt);
	}
	
	
	//Test same user is not saved twice
	@Test
	public void  checkExistingUserTest() {
		//given
		User existingUser = new User (1L, "john_doe");
		given(userRepository.findByAlias("john_doe")).willReturn(Optional.of(existingUser));
		ChallengeAttemptDTO attemptDTO = 
				new ChallengeAttemptDTO(50, 60, "john_doe", 5000);
		
		//when
		ChallengeAttempt resultAttempt = challengeService.testAttempt(attemptDTO);
		
		//then
		then(resultAttempt.isCorrect()).isFalse();
		then(resultAttempt.getUser()).isEqualTo(existingUser);
		//verify that this user is not saved again, but the attempt was saved
		verify(userRepository, never()).save(any());
		verify(attemptRepository).save(resultAttempt);
	};
	
}
