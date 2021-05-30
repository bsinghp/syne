package com.aq.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecieverController {
	@Value("${message}")
	String message;

	@RequestMapping(value = "/msg/", method = RequestMethod.GET)
	public String welcome() {
		return message;
	}

}
