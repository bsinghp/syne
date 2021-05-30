package com.synechron;

import java.net.ConnectException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

@Controller
public class NumberController {

	//private String evenServiceBaseUrl = "http://localhost:8082";
	
	@Value("${even.service.base.url}")
	private String evenServiceBaseUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@GetMapping
	public String getIndexPage() {
		return "index";
	}
	
	@PostMapping("/evencheck")
	public String checkEvenNumber(@RequestParam int number, HttpSession session) {
		String url = evenServiceBaseUrl + "/" + number;
		ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
		if(response.getBody()) {
			session.setAttribute("message", number + " is an even number");
		}
		else {
			session.setAttribute("message", number + " is not an even number");
		}
		return "index";
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
