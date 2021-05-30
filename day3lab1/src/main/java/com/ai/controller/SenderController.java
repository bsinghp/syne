package com.ai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SenderController {

	@RequestMapping(value = "/welcome/", method = RequestMethod.GET)
	public String welcome() {
		return "messaging service started";
	}

}
