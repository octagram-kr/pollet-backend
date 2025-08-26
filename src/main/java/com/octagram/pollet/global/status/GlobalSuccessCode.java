package com.octagram.pollet.global.status;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GlobalSuccessCode implements BaseCode {

	GLOBAL_SUCCESS_SAMPLE(HttpStatus.OK, "GS_000", "전역 성공 샘플");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
