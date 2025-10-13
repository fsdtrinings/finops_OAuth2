package com.mkj.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FinOpsSpringAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinOpsSpringAppApplication.class, args);
	}

}
