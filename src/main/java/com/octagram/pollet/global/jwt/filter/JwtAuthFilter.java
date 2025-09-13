package com.octagram.pollet.global.jwt.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
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
    private static final List<String> NO_CHECK_URL_PREFIXES = List.of(
		"/api/v1/auth/test-jwt/**",
		"/auth-test/login",
		"/oauth2/authorization/**",
		"/login/oauth2/**",
		"/api/v1/auth/reissue",
		"/health",
		"/actuator/**",
		"/swagger-ui/**",
		"/v3/api-docs/**",
		// 비회원도 접근 가능한 엔드포인트
		"/api/v1/surveys/{surveyId}",
		"/api/v1/surveys/{surveyId}/**",
		"/api/v1/surveys/count",
		"/api/v1/surveys/recent",
		"/api/v1/surveys"
	);

	private final JwtService jwtService;
	private final AntPathMatcher pathMatcher = new AntPathMatcher();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
		if (SecurityContextHolder.getContext().getAuthentication() != null ||
            isNoCheckUrl(request.getRequestURI())) {
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

    private boolean isNoCheckUrl(String uri) {
        return NO_CHECK_URL_PREFIXES.stream().anyMatch(s -> pathMatcher.match(s, uri));
    }

	private void setAuthentication(String memberId, HttpServletRequest request, List<SimpleGrantedAuthority> authorities) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
			memberId, null, authorities
		);

		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
