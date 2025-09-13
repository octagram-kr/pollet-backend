package com.octagram.pollet.auth.domain.status;

import org.springframework.http.HttpStatus;

import com.octagram.pollet.global.domain.status.BaseCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthSuccessCode implements BaseCode {

	LOGOUT_SUCCESS(HttpStatus.OK, "AS_001", "로그아웃이 정상적으로 완료되었습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
