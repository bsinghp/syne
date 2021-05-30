package com.aq;

import java.net.ConnectException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class MovieController {
	private List<String> movieDetails = new ArrayList<>();

	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${movie.queue}")
	private String movieQueue;

	@GetMapping
	public String getIndexPage() {
		return "index";
	}

	@PostMapping("/fetchmoviedetails")
	public String getMovieName(@RequestParam String movie, HttpSession session) {
		System.out.println("Movie name : " + movie);
		jmsTemplate.convertAndSend(movieQueue, movie);
		return "Details for " + movie + " will be fetched soon";
	}
	@JmsListener(destination = "${movie.details.queue}")
	public void receiveMovieDetails(String details) {
		movieDetails.add(details);
	}
	
	//@GetMapping("/details")
	@RequestMapping(value="/details", method = RequestMethod.GET, headers="Accept=application/json")
	public List<String> getMovieDetails() {
		return movieDetails;
	}

	@ExceptionHandler(ConnectException.class)
	public HttpEntity<ErrorInfo> handleConnectException(ConnectException ex, WebRequest req) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setReason(ex.getLocalizedMessage());
		errorInfo.setPath(req.getDescription(false));
		errorInfo.setTime(LocalDateTime.now().toLocalTime().toString());
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
