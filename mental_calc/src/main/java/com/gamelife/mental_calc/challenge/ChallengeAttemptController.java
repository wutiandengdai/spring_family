package com.gamelife.mental_calc.challenge;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/*
 * Valid - to valid request parameter
 * If @Service is missing from parameter, Dependency injection will fail
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/attempts")
@CrossOrigin(origins = "http://localhost:3000")
public class ChallengeAttemptController {

	private final ChallengeService challengeService;
	
	@PostMapping
	ResponseEntity<ChallengeAttempt> postResult(@RequestBody @Valid ChallengeAttemptDTO challengeAttemptDTO){
		return ResponseEntity.ok(challengeService.testAttempt(challengeAttemptDTO));
	}
	
	@GetMapping
	ResponseEntity<List<ChallengeAttempt>> getUserAttempts(@RequestParam("alias") String alias){
		return ResponseEntity.ok(challengeService.getRecentAttemptsByUser(alias));
	}
}
