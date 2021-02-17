package com.mental_calc.multiplication.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.*;

/**
 * Entity - db record
 * Data - getter, Setter, toString, equalTo 
 * @author lei
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	private String alias;
	
	public User(final String userAlias) {
		this(null, userAlias);
	}
}
