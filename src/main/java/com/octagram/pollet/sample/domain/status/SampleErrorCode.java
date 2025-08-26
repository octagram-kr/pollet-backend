package com.octagram.pollet.sample.domain.status;

import org.springframework.http.HttpStatus;

import com.octagram.pollet.global.status.BaseCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SampleErrorCode implements BaseCode {

	SAMPLE_CREATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SE_001", "샘플 생성에 실패했습니다."),
	SAMPLE_NOT_FOUND(HttpStatus.NOT_FOUND, "SE_002", "샘플을 찾을 수 없습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
