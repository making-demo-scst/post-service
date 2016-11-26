package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = "com.example:customer-service", workOffline = true)
public class PostServiceConsumerTest {
	@Rule
	public OutputCapture capture = new OutputCapture();

	@Autowired
	StubTrigger stubTrigger;

	@Test
	public void sendWelcomePack() throws Exception {
		stubTrigger.trigger("create-customer");
		assertThat(capture.toString()).endsWith(
				"Send welcome pack CustomerCreateEvent{id='foo', name='John', email='john@example.com', address='null'}"
						+ System.lineSeparator());
	}

}