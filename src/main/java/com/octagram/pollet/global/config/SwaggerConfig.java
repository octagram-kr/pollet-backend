package com.octagram.pollet.global.config;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		Info info = new Info()
			.title("Pollet API")
			.description("Octagram - Pollet API 문서")
			.version("v1.0")
			.contact(new Contact()
				.name("Team Octagram")
				.url("https://github.com/octagram-kr"));

		String securitySchemeName = "bearerAuth";
		SecurityRequirement securityRequirement = new SecurityRequirement().addList(securitySchemeName);
		Components components = new Components()
			.addSecuritySchemes(securitySchemeName, new SecurityScheme()
				.name(securitySchemeName)
				.type(SecurityScheme.Type.HTTP)
				.scheme("bearer")
				.bearerFormat("JWT"));

		return new OpenAPI()
			.info(info)
			.servers(List.of(
				new Server()
					.url("http://localhost:8080")
					.description("로컬 서버"),
				new Server()
					.url("https://pollet-dev.friox.dev")
					.description("개발 서버")
			))
			.addSecurityItem(securityRequirement)
			.components(components);
	}
}
