package com.octagram.pollet.auth.application;

import java.util.Collections;
import java.util.Map;

import com.octagram.pollet.member.domain.model.type.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.octagram.pollet.auth.infrastructure.CustomOAuth2User;
import com.octagram.pollet.auth.infrastructure.OAuthAttributes;
import com.octagram.pollet.member.domain.model.type.AuthProvider;
import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.member.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserOAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private static final String GOOGLE = "google";
	private static final String KAKAO = "kakao";

	private final MemberRepository memberRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// OAuth2 로그인 유저 정보 가져오기
		OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

		String provider = userRequest.getClientRegistration().getRegistrationId();
		AuthProvider authProvider = getAuthProvider(provider);
		String userNameAttributeName = userRequest.getClientRegistration()
			.getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
		Map<String, Object> attributes = oAuth2User.getAttributes();

		// provider, oAuth2UserInfo 정보 반환
		OAuthAttributes extractAttributes = OAuthAttributes.of(authProvider, userNameAttributeName, attributes);

		// member가 있다면 객체 반환, 없다면 새로 생성 후 반환
		Member createMember = getMember(provider, extractAttributes, authProvider);

		// DefaultOAuth2User를 구현한 CustomOAuth2User 반환
		return new CustomOAuth2User(
			Collections.singleton(new SimpleGrantedAuthority(createMember.getRole().name())),
			attributes,
			userNameAttributeName,
			createMember.getMemberId(),
			createMember.getEmail(),
			createMember.getRole()
		);
	}

	private AuthProvider getAuthProvider(String registrationId) {
		if (GOOGLE.equals(registrationId)) {
			return AuthProvider.GOOGLE;
		}
		return AuthProvider.KAKAO;
	}

	private Member getMember(String provider, OAuthAttributes attributes, AuthProvider authProvider) {
		return memberRepository.findByMemberIdAndRole(
                provider + attributes.oAuth2UserInfo().getId(),
                        Role.MEMBER)
			.orElse(saveMember(provider, attributes, authProvider));
	}

	private Member saveMember(String provider, OAuthAttributes attributes, AuthProvider authProvider) {
		return memberRepository.save(attributes.toEntity(authProvider, provider, attributes.oAuth2UserInfo()));
	}
}
