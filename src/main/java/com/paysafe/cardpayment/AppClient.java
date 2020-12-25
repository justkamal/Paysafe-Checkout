package com.paysafe.cardpayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.paysafe.cardpayment"})
@ComponentScan( basePackages = {"com.paysafe.cardpayment.*"})
public class AppClient {
	
	public static void main(String[] args) {
		SpringApplication.run(AppClient.class, args);
	}

}
