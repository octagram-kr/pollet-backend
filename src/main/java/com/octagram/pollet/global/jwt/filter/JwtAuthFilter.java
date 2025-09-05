package com.octagram.pollet.global.jwt.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.octagram.pollet.global.exception.BusinessException;
import com.octagram.pollet.global.jwt.service.JwtService;
import com.octagram.pollet.global.jwt.status.JwtErrorCode;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

	private static final String NO_CHECK_URL = "/auth/login";

	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
		if (SecurityContextHolder.getContext().getAuthentication() != null ||
			request.getRequestURI().startsWith(NO_CHECK_URL)) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = jwtService.extractAccessToken(request)
			.orElse(null);

		if (token == null) {
			throw new BusinessException(JwtErrorCode.TOKEN_INVALID);
		}

		jwtService.validateToken(token);

		jwtService.getMemberIdFromToken(token).ifPresent(memberId -> {
			UserDetails userDetails = userDetailsService.loadUserByUsername(memberId);
			setAuthentication(userDetails, request);
		});

		filterChain.doFilter(request, response);
	}

	private void setAuthentication(UserDetails userDetails, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
			userDetails, null, userDetails.getAuthorities()
		);

		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
