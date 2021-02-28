package com.mental_calc.multiplication.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

	/**
	 * Optional - in support of no user found
	 * @param alias
	 * @return
	 */
	Optional<User> findByAlias(final String alias);
	
	List<User> findAllByIdIn(final List<Long> ids);
}
