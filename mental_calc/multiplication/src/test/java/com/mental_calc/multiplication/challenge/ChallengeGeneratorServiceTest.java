package com.mental_calc.multiplication.challenge;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mental_calc.multiplication.challenge.Challenge;
import com.mental_calc.multiplication.challenge.ChallengeGeneratorService;
import com.mental_calc.multiplication.challenge.ChallengeGeneratorServiceImpl;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ChallengeGeneratorServiceTest {
	private ChallengeGeneratorService challengeGeneratorService;
	
	@Spy
	private Random random;
	
	@BeforeEach
	public void setUp() {
		challengeGeneratorService = new ChallengeGeneratorServiceImpl(random);
	}

	@Test
	public void generateRandomFactorIsBetweenExpectedLimit(){
		//generate random number (0`89) + 11
		//given
		given(random.nextInt(89)).willReturn(20, 30);
		//when
		Challenge challenge = challengeGeneratorService.generateChallenge();
		//then
		//(20 + 11, 30+11)
		then(challenge).isEqualTo(new Challenge(31, 41));
	}
}
