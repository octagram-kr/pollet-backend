package com.octagram.pollet.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import com.octagram.pollet.auth.infrastructure.CustomOAuth2User;
import com.octagram.pollet.global.jwt.service.JwtService;
import com.octagram.pollet.member.domain.model.type.Role;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

	private final JwtService jwtService;

	@Override
	public void onAuthenticationSuccess(
		HttpServletRequest request,
		HttpServletResponse response,
		Authentication authentication
	) throws IOException, ServletException {
		log.info("OAuth2 Login 성공");
		try {
			CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

			if(oAuth2User.getRole() == Role.GUEST) {
				String accessToken = jwtService.createAccessToken(oAuth2User.getEmail(), oAuth2User.getRole().toString());
				jwtService.sendAccessToken(response, accessToken);

				// TODO: 프론트 추가정보 작성 폼 주소로 리다이렉트
				response.sendRedirect("/");
			} else {
				loginSuccess(response, oAuth2User);
			}
		} catch (Exception e) {
			throw e;
		}

	}

	// TODO: access, refresh 토큰 생성
	private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
		String accessToken = jwtService.createAccessToken(oAuth2User.getEmail(), oAuth2User.getRole().toString());
		String refreshToken = jwtService.createRefreshToken();

		jwtService.sendAccessToken(response, accessToken);
		jwtService.setRefreshToken(response, refreshToken);

		// TODO: 프론트 메인 페이지 주소로 리다이렉트
		response.sendRedirect("/");
	}
}