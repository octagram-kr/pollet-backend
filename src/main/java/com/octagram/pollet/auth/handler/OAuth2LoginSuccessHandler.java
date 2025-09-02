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
import com.octagram.pollet.member.domain.model.type.Role;
import com.octagram.pollet.member.infrastructure.MemberRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

	// TODO: JWT 구현 후 관련 코드 추가
	private final MemberRepository memberRepository;

	@Override
	public void onAuthenticationSuccess(
		HttpServletRequest request,
		HttpServletResponse response,
		Authentication authentication
	) throws IOException, ServletException {
		log.info("OAuth2 Login 성공");
		try {
			CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

			// User의 Role이 GUEST일 경우 처음 요청한 회원이므로 회원가입 페이지로 리다이렉트
			if(oAuth2User.getRole() == Role.GUEST) {
				// TODO: jwt 토큰 발급 코드 추가
			} else {
				// 로그인에 성공한 경우 access, refresh 토큰 생성
				loginSuccess(response, oAuth2User);
			}
		} catch (Exception e) {
			throw e;
		}

	}

	// TODO: access, refresh 토큰 생성
	private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
	}
}