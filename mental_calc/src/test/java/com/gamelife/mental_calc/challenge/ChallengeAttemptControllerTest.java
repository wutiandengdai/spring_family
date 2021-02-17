package com.gamelife.mental_calc.challenge;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.gamelife.mental_calc.user.User;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;

/**
 * autoConfigureJsonTesters - tells spring configure JacksonTester type (Autowired）
 * WebMVCTest - Presentation layer test, load validation, serialization, security around the controller
 * MockBean - mock other layers, and set the expected values in given().willReturn();
 * @author lei
 *
 */
@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(ChallengeAttemptController.class)
public class ChallengeAttemptControllerTest {

	@MockBean
	private ChallengeService challengeService;
	
	//provide a test context but doesn't load the real server
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private JacksonTester<ChallengeAttemptDTO> jsonRequestAttempt;
	
	@Autowired
	private JacksonTester<ChallengeAttempt> jsonResultAttempt;
	
	@Test
	void postValidResult() throws IOException, Exception {
		//given 
		User user = new User (1L, "john");
		long attemptId = 5L;
		ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO (50, 70, user.getAlias(), 3500);
		ChallengeAttempt expectedResponse = new ChallengeAttempt (attemptId, user, 50, 70, 3500, true);
		given(challengeService.testAttempt (eq(attemptDTO))).willReturn(expectedResponse);
		
		//when
		MockHttpServletResponse response = mvc.perform(
				post("/attempts").contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequestAttempt.write(attemptDTO).getJson())
		).andReturn().getResponse();
		
		//then
		then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		then(response.getContentAsString()).isEqualTo(
				jsonResultAttempt.write(expectedResponse).getJson()
		);
	}
	
	@Test
	void postInvalidResult() throws IOException, Exception {
		//given invalid data input
		ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO (2000, -70, "Lily", 3500);
		
		//when
		MockHttpServletResponse response = mvc.perform(
				post("/attempts").contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequestAttempt.write(attemptDTO).getJson())
		).andReturn().getResponse();
		
		//then
		then(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}
}
