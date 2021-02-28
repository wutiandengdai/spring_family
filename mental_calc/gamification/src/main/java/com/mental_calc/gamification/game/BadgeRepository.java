package com.mental_calc.gamification.game;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mental_calc.gamification.game.domain.BadgeCard;

public interface BadgeRepository extends CrudRepository<BadgeCard, Long>{

	List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(Long userId);
}
