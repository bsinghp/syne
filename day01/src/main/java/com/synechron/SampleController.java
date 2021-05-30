package com.synechron;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
	
	@GetMapping("/home")
	public String hello() {
		return "my home";
	}
	

}
