package com.modanisa.devopschallenge;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class DevopsChallengeApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void givenTodoDoesNotExists_whenTodoInfoIsRetrieved_then404IsReceived()
			throws ClientProtocolException, IOException {

		// Given
		HttpUriRequest request = new HttpGet( "http://localhost:8081/todos");

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

		// Then
		Assert.assertThat (
				httpResponse.getStatusLine().getStatusCode(),
				CoreMatchers.equalTo(HttpStatus.SC_NOT_FOUND));
	}
}
