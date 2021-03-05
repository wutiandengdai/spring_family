package com.mental_calc.gamification.game;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.mental_calc.gamification.challenge.ChallengeSolvedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class GameEventHandler {

	private final GameService gameService;
	
	/**
	 * RabbitListener supports:
	 * 1. Declare exchanges, queues and bindings
	 * 2. Receive messages from multiple queues with the same method
	 * 3. Process the message headers by annotating extra qrguments with @Header or @Headers
	 * 4. Inject a channel argument, so to control acknowledgements
	 * 5. Implement a request-response pattern
	 * 6. Move the annotaton level and use @RabbitHandler for methods
	 * @param event
	 */
	@RabbitListener( queues="${amqp.queue.gamification}")
	void handleMultiplicationSolved(final ChallengeSolvedEvent event) {
		log.info("Challenge Solved Event received: {}", event.getAttemptId());
		
		try {
			gameService.newAttempt(event);
		}catch (final Exception e) {
			log.error("Error occured when trying to process ChallengeSovedEvent", e);
			
			//avoid this event to be re-queued and reprocessed
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
}
