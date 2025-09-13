package com.octagram.pollet.global.jwt.filter;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.octagram.pollet.global.presentation.dto.ApiResponse;
import com.octagram.pollet.global.exception.BusinessException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
	) throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (BusinessException e) {
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setStatus(e.getErrorCode().getStatus().value());
			ApiResponse<?> apiResponse = ApiResponse.fail(e.getErrorCode(), e.getCause());
			final ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getOutputStream(), apiResponse);
		}
	}
}
