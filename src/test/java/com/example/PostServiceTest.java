package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

	@Autowired
	Sink sink;

	@Rule
	public OutputCapture capture = new OutputCapture();

	@Test
	public void sendWelcomePack() throws Exception {
		CustomerCreateEvent event = new CustomerCreateEvent();
		event.setId("a");
		event.setName("b");
		event.setEmail("c");
		event.setAddress("d");
		sink.input().send(MessageBuilder.withPayload(event).build());

		assertThat(capture.toString()).endsWith(
				"Send welcome pack CustomerCreateEvent{id='a', name='b', email='c', address='d'}"
						+ System.lineSeparator());
	}

}