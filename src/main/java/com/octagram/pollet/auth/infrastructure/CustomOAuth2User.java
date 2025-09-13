package com.octagram.pollet.auth.infrastructure;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import com.octagram.pollet.member.domain.model.type.Role;

import lombok.Getter;

@Getter
public class CustomOAuth2User extends DefaultOAuth2User {

	private final String memberId;
	private final String email;
	private final Role role;

	public CustomOAuth2User(
		Collection<? extends GrantedAuthority> authorities,
		Map<String, Object> attributes,
		String nameAttributeKey,
		String memberId,
		String email,
		Role role
	) {
		super(authorities, attributes, nameAttributeKey);
		this.memberId = memberId;
		this.email = email;
		this.role = role;
	}
}
