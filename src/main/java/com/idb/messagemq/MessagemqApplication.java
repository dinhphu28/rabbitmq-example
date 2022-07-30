package com.idb.messagemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MessagemqApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagemqApplication.class, args);
	}

}
