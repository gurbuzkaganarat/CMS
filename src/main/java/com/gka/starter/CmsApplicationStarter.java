package com.gka.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.gka"})
@EntityScan(basePackages = {"com.gka"})
@EnableJpaRepositories(basePackages = {"com.gka"})
@SpringBootApplication
public class CmsApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(CmsApplicationStarter.class, args);
	}

}
