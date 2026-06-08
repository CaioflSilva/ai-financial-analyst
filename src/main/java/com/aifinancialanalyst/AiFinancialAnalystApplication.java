package com.aifinancialanalyst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AiFinancialAnalystApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiFinancialAnalystApplication.class, args);
	}
}