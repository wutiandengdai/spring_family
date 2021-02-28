package com.mental_calc.gamification.game;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mental_calc.gamification.game.domain.LeaderBoard;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/leaders")
@RequiredArgsConstructor
public class LeaderBoardController {

	private final LeaderBoardService leaderBoardService;
	
	@GetMapping
	public List<LeaderBoard> getLeaderBoard(){
		return leaderBoardService.getLeaderBoard();
	}
}
