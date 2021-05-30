package com.synechron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieDetailsFetcher {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${omdb.base.url}")
	private String omdbBaseUrl;
	
	@Value("${movie.details.queue}")
	private String movieDetailsQueue;
	
	@JmsListener(destination = "${movie.input.queue}")
	public void receiveMovieInput(String movie) {
		ResponseEntity<String> response = restTemplate.getForEntity(omdbBaseUrl + movie, String.class);
		String details = response.getBody();
		jmsTemplate.convertAndSend(movieDetailsQueue, details);
	}
	
}
