package com.synechron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class Day03ReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day03ReceiverApplication.class, args);
	}

}
