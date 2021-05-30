package com.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Person2Controller {

	@Autowired
	RestTemplate restTemplate;

	private String url = "http://localhost:8081/person/";

	@RequestMapping(value = "/person/", method = RequestMethod.GET)
	public String getAllPerson() {
		return restTemplate.getForObject(url, String.class);
	}

	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
	public String getById(@PathVariable("id") int id) {
		return restTemplate.getForObject(url + id, String.class);
	}

}
