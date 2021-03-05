package com.mental_calc.multiplication.configuration;

import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfiguratoin {

	@Bean
	public TopicExchange challengesTopicExchange(@Value("${amqp.exchange.attempts}") 
			final String exchangeName) {
		//topic durable, so message remains after system restart
		return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
	}
	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		//override java object serializer with object serializer
		return new Jackson2JsonMessageConverter();
	}
}
