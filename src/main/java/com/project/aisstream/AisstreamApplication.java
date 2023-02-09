package com.project.aisstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AisstreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(AisstreamApplication.class, args);
	}

}
