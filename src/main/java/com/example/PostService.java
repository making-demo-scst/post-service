package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
public class PostService {
	private final Logger log = LoggerFactory.getLogger(PostService.class);

	@StreamListener(Sink.INPUT)
	public void sendWelcomePack(CustomerCreateEvent event) {
		log.info("Send welcome pack {}", event);
	}
}
