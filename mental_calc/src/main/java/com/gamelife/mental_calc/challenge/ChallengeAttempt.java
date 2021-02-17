package com.gamelife.mental_calc.challenge;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gamelife.mental_calc.user.User;

import lombok.*;

/**
 * Record the attempt by valid users
 * @author lei
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeAttempt {

	@Id
	@GeneratedValue
	private Long id;
	
	//triggers a query to the field value only when getter is called
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;
	private int factorA;
	private int factorB;
	private int resultAttempt;
	private boolean correct;
}
