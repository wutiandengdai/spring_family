package com.mental_calc.gamification.game;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mental_calc.gamification.challenge.ChallengeSolvedDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attempts")
@RequiredArgsConstructor
public class GameController {

	private final GameService gameService;
	
	//Default behavior of a void return is HttpStatus.OK, unless exception throw
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	void postResult(@RequestBody ChallengeSolvedDTO dto) {
		gameService.newAttempt(dto);
	}
}
