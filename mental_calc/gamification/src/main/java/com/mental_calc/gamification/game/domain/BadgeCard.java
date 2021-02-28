package com.mental_calc.gamification.game.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadgeCard {
	@Id
	@GeneratedValue
	private Long badgeId;
	
	private Long userId;
	@EqualsAndHashCode.Exclude
	private Long badgeTimestamp;
	private BadgeType badgeType;
	
	public BadgeCard(final Long userId, final BadgeType badgeType) {
		this(null, userId, System.currentTimeMillis(), badgeType);
	}
}
