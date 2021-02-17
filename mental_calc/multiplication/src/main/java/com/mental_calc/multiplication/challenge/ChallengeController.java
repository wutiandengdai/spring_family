package com.mental_calc.multiplication.challenge;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/challenges")
@CrossOrigin(origins = "http://localhost:3000")
public class ChallengeController {

	//requiredArgsConstructor create a constructor with challengeGeneratorService as argument, 
	//since the argument uses final and private
	//Spring uses dependency injection and will find a beam implementing the interface
	private final ChallengeGeneratorService challengeGeneratorService;
	
	@GetMapping("/random")
	Challenge getChallenge() {
		Challenge challenge = challengeGeneratorService.generateChallenge();
		log.info("Generated Challenge : {} ", challenge);
		
		//automatically converts to json (spring-boot-autoconfiguration, spring-boot-starter-json)
		return challenge;
	}
	
	
}
