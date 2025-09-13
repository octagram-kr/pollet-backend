package com.octagram.pollet.survey.domain.status;

import org.springframework.http.HttpStatus;

import com.octagram.pollet.global.domain.status.BaseCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TagErrorCode implements BaseCode {

	TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "TE_001", "태그를 찾을 수 없습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
