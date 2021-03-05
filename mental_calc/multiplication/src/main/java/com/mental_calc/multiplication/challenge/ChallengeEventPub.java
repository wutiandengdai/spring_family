package com.mental_calc.multiplication.challenge;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChallengeEventPub {
	
	private final AmqpTemplate amqpTemplate;
	private final String challengesTopicExchange;
	
	//private final RabbitTemplate rabbitTemplate;
	
	public ChallengeEventPub(final AmqpTemplate amqpTemplate, 
			@Value("${amqp.exchange.attempts}") final String challengesTopicExchange) {
		this.amqpTemplate = amqpTemplate;
		this.challengesTopicExchange = challengesTopicExchange;
	}
	
	public void challengeSolved(final ChallengeAttempt challengeAttempt) {
		ChallengeSolvedEvent event = buildEvent(challengeAttempt);
		
		//Routing by key "attempt.correct", "attempt.wrong"
		String routingKey = "attempt." + (event.isCorrect()? "correct" : "wrong");
		amqpTemplate.convertAndSend(challengesTopicExchange, routingKey, event);
		
		//If we use rabbitMQ template
//		MessageProperties properties = MessagePropertiesBuilder.newInstance()
//				.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
//				.build();
//		rabbitTemplate.getMessageConverter().toMessage(challengesTopicExchange, properties);
//		rabbitTemplate.convertAndSend(challengesTopicExchange, routingKey, event);
	}
	
	//Transfers domain object to event object
	private ChallengeSolvedEvent buildEvent(final ChallengeAttempt attempt) {
		return new ChallengeSolvedEvent(attempt.getId(), attempt.isCorrect(), attempt.getFactorA(),
				attempt.getFactorB(), attempt.getUser().getId(), attempt.getUser().getAlias());
	}
}
