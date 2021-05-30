package com.aq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieReciever {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${omdb.base.url}")
	private String omdbBaseUrl;
	
	@Value("${movie.details.queue}")
	private String movieDetailsQueue;
	
	@JmsListener(destination = "${movie.input.queue}")
	public void movieReciever(String movie) {
		double cmp = Math.random() * 1000;
		System.out.println("****CMP of " + movie + " is " + cmp);
		//push http://www.omdbapi.com/?i=tt3896198&apikey=52d1c7f&t=
		ResponseEntity<String> response = restTemplate.getForEntity(omdbBaseUrl + movie, String.class);
		String details = response.getBody();
		jmsTemplate.convertAndSend(movieDetailsQueue, details);
	}

}
