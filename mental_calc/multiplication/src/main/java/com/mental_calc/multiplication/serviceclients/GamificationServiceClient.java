package com.mental_calc.multiplication.serviceclients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mental_calc.multiplication.challenge.ChallengeAttempt;
import com.mental_calc.multiplication.challenge.ChallengeSolvedDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GamificationServiceClient {

	private final RestTemplate restTemplate;
	private final String gamificationHostUrl;
	
	public GamificationServiceClient(final RestTemplateBuilder builder, 
			@Value("${service.gamification.url}") final String gamificationHostUrl) {
		restTemplate = builder.build();
		this.gamificationHostUrl = gamificationHostUrl;
	}
	
	public boolean sendAttepmt(final ChallengeAttempt attempt) {
		try {
			ChallengeSolvedDTO dto = new ChallengeSolvedDTO(attempt.getId(),
					attempt.isCorrect(), attempt.getFactorA(),
					attempt.getFactorB(), attempt.getUser().getId(),
					attempt.getUser().getAlias());
			
			//send the dto created from challengeAttempt to gamification attempts
			ResponseEntity<String> r = restTemplate.postForEntity(gamificationHostUrl + "/attempts", dto, String.class);
			log.info("Gamification Serivce Response: {}", r.getStatusCode());
			
			return r.getStatusCode().is2xxSuccessful();
			
		} catch (Exception e) {
			log.error("There was a problem sending the attempt. " , e);
			return false;
		}
	}
}
