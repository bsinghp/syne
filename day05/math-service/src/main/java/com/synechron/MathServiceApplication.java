package com.synechron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MathServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathServiceApplication.class, args);
	}

}
