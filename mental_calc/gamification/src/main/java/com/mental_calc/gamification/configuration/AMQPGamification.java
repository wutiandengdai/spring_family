package com.mental_calc.gamification.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@Configuration
public class AMQPGamification {
	
	@Bean
	public TopicExchange challengesTopicExchange(
			@Value("${amqp.exchange.attempts}") final String exchangeName) {
		return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
	}
	
	
	@Bean
	public Queue GamificationQueue (
			@Value("${amqp.queue.gamification}") final String queueName) {
		return QueueBuilder.durable(queueName).build();
	}
	
	//Bind queue of correct attempts to exchange
	@Bean
	public Binding correctAttemptsBinding(final Queue gamificationQueue, final TopicExchange attemptsExchange) {
		return BindingBuilder.bind(gamificationQueue).to(attemptsExchange).with("attempt.correct");
	}
	
	
	@Bean
	public MessageHandlerMethodFactory messageHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		
		final MappingJackson2MessageConverter jsonConverter = new MappingJackson2MessageConverter();
		jsonConverter.getObjectMapper().registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
		
		factory.setMessageConverter(jsonConverter);
		return factory;
	}
	
	//to have broker notify subscribers
	@Bean
	public RabbitListenerConfigurer rabbitListenerConfigurer (
			final MessageHandlerMethodFactory messageHandlerMethodFactory) {
		return (c) -> c.setMessageHandlerMethodFactory(messageHandlerMethodFactory);
	}
}
