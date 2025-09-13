package com.octagram.pollet.global.domain.status;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorCode implements BaseCode {

	GLOBAL_ERROR_SAMPLE(HttpStatus.INTERNAL_SERVER_ERROR, "GE_000", "전역 오류 샘플"),
	REQUEST_BODY_INVALID(HttpStatus.BAD_REQUEST, "GE_001", "파라미터 유효성 검증 실패"),
	REQUEST_BODY_NOT_READABLE(HttpStatus.BAD_REQUEST, "GE_002", "요청 메시지 변환 실패 (누락 등)"),
	REQUEST_PARAM_REQUIRED(HttpStatus.BAD_REQUEST, "GE_003", "필수 요청 파라미터 누락"),
	METHOD_PARAM_INVALID(HttpStatus.BAD_REQUEST, "GE_004", "메서드 파라미터 유효성 검증 실패"),
	METHOD_PARAM_TYPE_INVALID(HttpStatus.BAD_REQUEST, "GE_005", "메서드 파라미터 타입 검증 실패"),
	HANDLER_NOT_FOUND(HttpStatus.NOT_FOUND, "GE_006", "존재하지 않는 엔드포인트"),
	UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "GE_999", "처리되지 않은 예외");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
