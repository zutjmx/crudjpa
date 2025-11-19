package com.zutjmx.curso.springboot.app.crudjpa.crudjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class CrudjpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudjpaApplication.class, args);
	}

}
