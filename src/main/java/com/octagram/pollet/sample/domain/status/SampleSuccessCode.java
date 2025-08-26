package com.octagram.pollet.sample.domain.status;

import org.springframework.http.HttpStatus;

import com.octagram.pollet.global.status.BaseCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SampleSuccessCode implements BaseCode {

	SAMPLE_READ_SUCCESS(HttpStatus.OK, "SS_001", "샘플을 성공적으로 조회했습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
