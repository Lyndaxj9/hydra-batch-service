package com.revature.hydra.batch.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;

@Controller
@EnableAutoConfiguration
@EnableJpaRepositories("com.revature.hydra.batch.data")
@ComponentScan(basePackages = {"com.revature.hydra.batch.controller", "com.revature.hydra.batch.service"})
@EntityScan("com.revature.beans")
public class BatchRepositoryServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BatchRepositoryServiceApplication.class, args);
	}
}
