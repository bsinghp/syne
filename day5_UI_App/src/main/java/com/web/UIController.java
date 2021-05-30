package com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UIController {
	@Autowired
	private RestTemplate restTemplate;

	@Value("${math.service.base.url}")
	private String mathServiceBaseUrl;

	@Value("${math.service.square.url}")
	private String mathServiceSquareUrl;

	@Value("${math.service.cube.url}")
	private String mathServiceCubeUrl;

	@Value("${mathdb.service.url}")
	private String mathDbServiceUrl;

	@Autowired
	private CircuitBreakerFactory cbFactory;

	/*
	 * @PostMapping("/square/{num}") public HttpEntity<Long> getSquare(@PathVariable
	 * int num) { String url = mathServiceBaseUrl + mathServiceSquareUrl + "/" +
	 * num; ResponseEntity<Long> result = restTemplate.getForEntity(url,
	 * Long.class); saveToDb("Square", num); return result; }
	 */
	@PostMapping("/square/{num}")
	public String getSquare(@PathVariable int num) {
		return cbFactory.create("squarecircuit").run(
				() -> {
					System.out.println("*********Tring to call math  service");
			return callMathServiceForGettingSquare(num);
		}, 
				t -> {
					System.out.println("========= call fall back service");
			return fallbackMethodForGettingSquare(num);
		});

		// return callMathServiceForGettingSquare(num);
	}

	private String callMathServiceForGettingSquare(int num) {
		String url = mathServiceBaseUrl + mathServiceSquareUrl + "/" + num;
		ResponseEntity<Long> result = restTemplate.getForEntity(url, Long.class);
		return result.getBody() + "";
	}

	private String fallbackMethodForGettingSquare(int num) {
		return "Oops!!! Service is down. Loading from cache " + (num * num);
	}

	@PostMapping("/cube/{num}")
	public HttpEntity<Long> getCube(@PathVariable int num) {
		String url = mathServiceBaseUrl + mathServiceCubeUrl + "/" + num;
		ResponseEntity<Long> result = restTemplate.getForEntity(url, Long.class);
		saveToDb("Cube", num);
		return result;
	}

	private void saveToDb(String operation, int num) {
		String url = mathDbServiceUrl + "/operation/" + operation + "/number/" + num;
		restTemplate.postForEntity(url, null, Integer.class);
	}

}