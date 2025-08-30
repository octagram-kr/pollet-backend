package com.octagram.pollet.auth.infrastructure;

import java.util.Map;

import com.octagram.pollet.auth.infrastructure.userinfo.GoogleOAuth2UserInfo;
import com.octagram.pollet.auth.infrastructure.userinfo.KakaoOAuth2UserInfo;
import com.octagram.pollet.auth.infrastructure.userinfo.OAuth2UserInfo;
import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.member.domain.model.type.AuthProvider;
import com.octagram.pollet.member.domain.model.type.Role;

import lombok.Builder;

@Builder
public record OAuthAttributes (
	String nameAttributeKey,
	OAuth2UserInfo oAuth2UserInfo
) {
	public static OAuthAttributes of(AuthProvider authProvider, String userNameAttributeName, Map<String, Object> attributes) {
		return switch (authProvider) {
			case GOOGLE -> ofGoogle(userNameAttributeName, attributes);
			case KAKAO -> ofKakao(userNameAttributeName, attributes);
		};
	}

	public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
			.nameAttributeKey(userNameAttributeName)
			.oAuth2UserInfo(new GoogleOAuth2UserInfo(attributes))
			.build();
	}

	public static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
			.nameAttributeKey(userNameAttributeName)
			.oAuth2UserInfo(new KakaoOAuth2UserInfo(attributes))
			.build();
	}

	public Member toEntity(AuthProvider authProvider, String provider, OAuth2UserInfo oAuth2UserInfo) {
		return Member.builder()
                .authProvider(authProvider)
                .memberId(provider + oAuth2UserInfo.getId())
                .profileImageUrl(oAuth2UserInfo.getImageUrl())
                .role(Role.GUEST)
			.build();
	}
}
