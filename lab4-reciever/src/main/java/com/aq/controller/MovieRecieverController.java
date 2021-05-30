package com.aq.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRecieverController {
	
	@Value("${movie.input.queue}")
	private String uriMovie;

	@RequestMapping(value="/details", method= RequestMethod.GET)
	public String getMovies() {
		return uriMovie;
	}
	
	

}
