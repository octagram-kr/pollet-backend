package com.octagram.pollet.global.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("Pollet API")
				.description("Octagram - Pollet API 문서")
				.version("v1.0")
				.contact(new Contact()
					.name("Team Octagram")
					.url("https://github.com/octagram-kr")))
			.servers(Collections.singletonList(
				new Server()
					.url("http://localhost:8080")
					.description("개발 서버")
			));
	}
}
