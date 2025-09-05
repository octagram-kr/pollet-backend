package com.octagram.pollet.global.jwt.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.octagram.pollet.global.exception.BusinessException;
import com.octagram.pollet.global.jwt.service.JwtService;
import com.octagram.pollet.global.jwt.status.JwtErrorCode;
import com.octagram.pollet.member.domain.model.type.Role;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

	private static final String ROLE_PREFIX = "ROLE_";
	private static final String NO_CHECK_URL = "/auth/login";

	private final JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
		if (SecurityContextHolder.getContext().getAuthentication() != null ||
			request.getRequestURI().startsWith(NO_CHECK_URL)) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = jwtService.extractAccessToken(request)
			.orElseThrow(() -> new BusinessException(JwtErrorCode.TOKEN_INVALID));

		jwtService.validateToken(token);

		String memberId = jwtService.getMemberIdFromToken(token)
			.orElseThrow(() -> new BusinessException(JwtErrorCode.TOKEN_INVALID));

		String role = jwtService.getRoleFromToken(token).orElse(Role.GUEST.name());
		List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(ROLE_PREFIX + role));

		setAuthentication(memberId, request, authorities);

		filterChain.doFilter(request, response);
	}

	private void setAuthentication(String memberId, HttpServletRequest request, List<SimpleGrantedAuthority> authorities) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
			memberId, null, authorities
		);

		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
