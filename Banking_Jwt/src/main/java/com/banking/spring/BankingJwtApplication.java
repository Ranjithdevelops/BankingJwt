package com.banking.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.banking"})
public class BankingJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingJwtApplication.class, args);
	}

}
