package com.octagram.pollet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PolletApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolletApplication.class, args);
	}

}
