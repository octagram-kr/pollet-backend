package com.octagram.pollet.auth.presentation.dto.response;

public record TestJwtGetResponse(
	String accessToken,
	String refreshToken
) {
}
