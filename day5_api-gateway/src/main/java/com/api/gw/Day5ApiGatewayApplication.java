package com.api.gw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class Day5ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day5ApiGatewayApplication.class, args);
	}
	// localhost:7000/sq/120 <-> http://localhost:8081/math/square/120
	// localhost:7000/cu/120 <-> http://localhost:8081/math/cube/120

	@Bean
	public RouteLocator configureRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("square-route",
						route -> route.path("/sq/**").filters(f -> f.stripPrefix(1).prefixPath("/math/square"))
								.uri("lb://math-service"))
				.route("cube-route",
						route -> route.path("/cu/**").filters(f -> f.stripPrefix(1).prefixPath("/math/cube"))
								.uri("lb://math-service"))
				.route("mathdb-route", route -> route.path("/db/**")
						.filters(f -> f.stripPrefix(1).prefixPath("/mathdb")).uri("lb://math-db-service"))
				.build();
	}
}
