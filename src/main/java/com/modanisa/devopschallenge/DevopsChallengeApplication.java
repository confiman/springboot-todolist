package com.modanisa.devopschallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages ={ "com.modanisa.devopschallenge"})
@EntityScan(basePackages = {"com.modanisa.devopschallenge.model"})
@EnableJpaRepositories(basePackages = {"com.modanisa.devopschallenge.repository"})
@SpringBootApplication
public class DevopsChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevopsChallengeApplication.class, args);
	}

}
