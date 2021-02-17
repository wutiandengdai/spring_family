package com.gamelife.mental_calc.challenge;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.Value;

/**
 * Min, Max, NotBlank, Positive validation
 * @author lei
 *
 */
@Value
public class ChallengeAttemptDTO {

	@Min(1) @Max(99)
	int factorA, factorB;
	
	@NotBlank
	String userAlias;
	
	//positive and change default message
	@Positive( message = "The result is unlikely to be negative here, try again?")
	int guess;
}
