package com.synechron;

import java.time.Duration;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class MyReactiveController {

	@GetMapping(value = "/numbers", produces = MediaType.APPLICATION_NDJSON_VALUE)
	public Flux<Integer> getNumbers() {
		return Flux.range(1, 10).delayElements(Duration.ofSeconds(1));
	}

	@GetMapping(value = "/languages", produces = MediaType.APPLICATION_NDJSON_VALUE)
	public HttpEntity<Flux<String>> getLanguages() {
		Flux<String> langs = Flux.just("Java", "Scala", "Ruby", "Golang");

		// If you want to get the values from a DB
		// use reactive db library
		return new ResponseEntity(langs, HttpStatus.OK);
	}
}
