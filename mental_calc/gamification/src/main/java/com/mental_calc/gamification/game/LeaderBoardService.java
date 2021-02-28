package com.mental_calc.gamification.game;

import java.util.List;

import com.mental_calc.gamification.game.domain.LeaderBoard;

public interface LeaderBoardService {

	/**
	 * return current leader board
	 * @return
	 */
	List<LeaderBoard> getLeaderBoard();
}
