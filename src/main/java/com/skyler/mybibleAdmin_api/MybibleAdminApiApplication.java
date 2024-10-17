package com.skyler.mybibleAdmin_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MybibleAdminApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybibleAdminApiApplication.class, args);
	}

}
