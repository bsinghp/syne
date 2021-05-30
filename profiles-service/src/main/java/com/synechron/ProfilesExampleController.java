package com.synechron;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfilesExampleController {

	@Value("${welcome}")
	private String welcome;
	
	@Value("${sendoff}")
	private String sendOff;
	
	@GetMapping
	public String getMessage() {
		return welcome + " " + sendOff;
	}
}
