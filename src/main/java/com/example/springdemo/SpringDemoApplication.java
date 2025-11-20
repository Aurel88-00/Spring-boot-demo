package com.example.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringDemoApplication {

	
	public static void main(String[] args) {
		System.out.println("Starting Spring Demo Application");
		SpringApplication.run(SpringDemoApplication.class, args);
	}

}
