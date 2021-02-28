package com.mental_calc.gamification.game.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NoArgsConstructor;

/**
 * Score generated after one attempt
 * @author lei
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreCard {

	//give a score of 10 by default
	public static final int DEFAULT_SCORE = 10;
	
	@Id
	@GeneratedValue
	private Long cardId;
	private Long userId;
	private Long attemptId;
	
	@Exclude
	private long scoreTimestamp;
	private int score;
	
	public ScoreCard(final Long userId, final Long attemptId) {
		this(null, userId, attemptId, System.currentTimeMillis(), DEFAULT_SCORE);
	}
}
