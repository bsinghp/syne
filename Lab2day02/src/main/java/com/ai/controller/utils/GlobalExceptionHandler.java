package com.ai.controller.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.ai.exception.AccountNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AccountNotFoundException.class)
	public HttpEntity<String> handleAccountNotFoundException(AccountNotFoundException ex, WebRequest req) {
		String message = "Error: Account " + ex.getMessage() + " is not found";
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public HttpEntity<String> handleException(Exception ex, WebRequest req) {
		String message = "Error: " + ex.getMessage();
		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
