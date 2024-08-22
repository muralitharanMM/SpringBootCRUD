package com.murali.learn.movieinfosystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MovieinfosystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieinfosystemApplication.class, args);
	}

}
