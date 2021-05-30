package com.synechron;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvenController {

	@GetMapping("/{num}")
	public HttpEntity<Boolean> checkEven(@PathVariable int num) {
		return new ResponseEntity<Boolean>(num % 2 == 0, HttpStatus.OK);
	}
}
