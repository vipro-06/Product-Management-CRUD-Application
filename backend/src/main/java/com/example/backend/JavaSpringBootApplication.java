package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main entry point of the Spring Boot application.
 * It starts the Spring application and embedded web server.
 */

@SpringBootApplication
@RestController
public class JavaSpringBootApplication {
	
	@GetMapping("/")
	public String root() {
		return "Server is running..";
	}

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringBootApplication.class, args);
	}

}
