package com.mental_calc.gamification.game;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mental_calc.gamification.game.domain.LeaderBoard;
import com.mental_calc.gamification.game.domain.ScoreCard;

public interface ScoreRepository extends CrudRepository<ScoreCard, Long>{

	// Get User's total score of all attempts
	@Query("SELECT SUM(s.score) FROM ScoreCard s WHERE s.userId = :userId  GROUP BY s.userId")
	Optional<Integer> getTotalScoreForUser(@Param("userId") Long userId);
	
	//Get the first 10 records in leader board
	@Query("SELECT NEW com.mental_calc.gamification.game.domain.LeaderBoard(s.userId, SUM(s.score)) "
			+ "FROM ScoreCard s GROUP BY s.userId ORDER BY SUM(s.score) DESC")
	List<LeaderBoard> listFirst10();
	
	
	List<ScoreCard> findByUserIdOrderByScoreTimestampDesc(final long userId);
}
