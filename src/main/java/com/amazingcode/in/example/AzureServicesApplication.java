package com.amazingcode.in.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AzureServicesApplication {

	private final static Logger logger = LoggerFactory.getLogger(AzureServicesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AzureServicesApplication.class, args);
		logger.info("Application started successfully...");
	}

}
