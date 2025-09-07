package com.octagram.pollet.global.aws.status;

import org.springframework.http.HttpStatus;

import com.octagram.pollet.global.status.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum S3ErrorCode implements BaseCode {

	FILE_EMPTY(HttpStatus.BAD_REQUEST, "S3E_001", "파일이 비어있습니다."),
	FILE_IO_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "S3E_002", "IO 오류가 발생했습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
