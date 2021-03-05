package com.mental_calc.multiplication.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * User Controller, get multiple users with user id list
 * We are injecting repository here directly, when we don't need a business layer.
 * @author lei
 *
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserRepository userRepository;
	
	@GetMapping("/{idList}")
	public List<User> getUsersByIdList (@PathVariable final List<Long> idList){
		return userRepository.findAllByIdIn(idList);
	}
}
