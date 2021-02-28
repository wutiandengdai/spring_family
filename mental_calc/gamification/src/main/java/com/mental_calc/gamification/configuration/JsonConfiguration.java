package com.mental_calc.gamification.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

/**
 * Global configuration , define the bean used for serialization
 * Json parse configuration
 * @author lei
 *
 */
@Configuration
public class JsonConfiguration {

	@Bean
	public Module hibernateModule() {
		return new Hibernate5Module();
	}
}
