package com.henry.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;

@SpringBootApplication
public class ReactiveSpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveSpringDemoApplication.class, args);
	}

}
