package com.synechron;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class StockQuoteFinder {
	
	@JmsListener(destination = "${stock.queue}")
	public void receiveStockSymbol(String symbol) {
		//Connect to Yahoo Finance, Google Finance and fetch the CMP
		double cmp = Math.random() * 1000;
		System.out.println("***** CMP of " + symbol + " is " + cmp);
	}
}
