package com.cobaltroad.fbauction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.cobaltroad.fbauction")
@EnableJpaRepositories
public class FbauctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FbauctionApplication.class, args);
	}

}
