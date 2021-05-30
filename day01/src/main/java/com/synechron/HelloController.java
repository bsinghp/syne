package com.synechron;

import java.time.LocalDateTime;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
	@GetMapping("/hello/{name}")
	public String getByName(@PathVariable("name") String name) {
		return "Hello : "+name;
	}
	
	@PostMapping("/now")
	public String postByName(@PathParam("name") String name) {
		return LocalDateTime.now().toLocalTime().toString();
	}
	
	@PostMapping("/person")
	public String getPersonByName(@RequestParam("name") String name, @RequestParam("age") int age) {
		return "name : "+name +" : age : "+age;
	}
}
