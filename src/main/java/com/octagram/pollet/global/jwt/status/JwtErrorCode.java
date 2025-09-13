package com.octagram.pollet.global.jwt.status;

import org.springframework.http.HttpStatus;

import com.octagram.pollet.global.domain.status.BaseCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JwtErrorCode implements BaseCode {

	TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "JE_001", "토큰이 유효하지 않습니다."),
	REFRESH_TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "JE_002", "리프레시 토큰이 유효하지 않습니다."),
	ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "JE_003" , "액세스 토큰이 만료되었습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
