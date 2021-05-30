package com.synechron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${stock.queue}")
	private String stockQueue;
	
	@GetMapping("/cmp/{symbol}")
	public String getCurrentMarketPrice(@PathVariable String symbol) {
		jmsTemplate.convertAndSend(stockQueue, symbol);
		return "You will receive the market price for  " + symbol + " soon";
	}
}
