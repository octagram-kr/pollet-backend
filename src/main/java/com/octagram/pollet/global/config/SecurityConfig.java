package com.octagram.pollet.global.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.octagram.pollet.auth.application.service.CustomUserOAuth2Service;
import com.octagram.pollet.auth.handler.OAuth2LoginFailureHandler;
import com.octagram.pollet.auth.handler.OAuth2LoginSuccessHandler;
import com.octagram.pollet.global.jwt.filter.JwtAuthFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

	private static final List<String> ALLOWED_ORIGINS = List.of(
		"http://localhost:8080",
		"http://localhost:3000"
		// 프론트엔드 배포 주소
	);
	private static final List<String> ALLOWED_METHOD = List.of("GET", "POST", "PUT", "DELETE", "PATCH");
	private static final String [] PERMIT_ALL_END_POINTS = {
		"/auth-test/login",
		"/oauth2/authorization/**",
		"/login/oauth2/**",
		"/api/v1/auth/reissue",
		"/health",
		"/actuator/**",
		"/swagger-ui/**"
	};

	private final JwtAuthFilter jwtAuthFilter;
	private final CustomUserOAuth2Service customUserOAuth2Service;
	private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;

	@Bean
	@Order(1)
	public SecurityFilterChain oAuth2FilterChain(HttpSecurity http) throws Exception {
		http
			.securityMatcher("/oauth2/**", "/login/oauth2/**")
			.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.oauth2Login(oauth2 -> oauth2
				.userInfoEndpoint(userInfoEndpointConfig ->
					userInfoEndpointConfig.userService(customUserOAuth2Service))
				.successHandler(oAuth2LoginSuccessHandler)
				.failureHandler(oAuth2LoginFailureHandler)
			);

		return http.build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain filterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
		http
			.securityMatcher("/api/v1/**")
			.cors(cors -> cors.configurationSource(corsConfigurationSource))
			.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http
			.authorizeHttpRequests(auth -> auth
				.requestMatchers(PERMIT_ALL_END_POINTS).permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.exceptionHandling(ex -> ex
				.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
			)
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowCredentials(true);
		configuration.setAllowedOrigins(ALLOWED_ORIGINS);
		configuration.setAllowedMethods(ALLOWED_METHOD);
		configuration.setAllowedHeaders(List.of("*"));

		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
