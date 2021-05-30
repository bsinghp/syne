package com.synechron;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Calculator {
	
	@GetMapping("/add/{a}/{b}")
	public HttpEntity<Integer> add(@PathVariable("a") int a, @PathVariable("b") int b){
	
		ResponseEntity<Integer> res=new ResponseEntity<>(a+b, HttpStatus.OK);
		return res;
	}

}
