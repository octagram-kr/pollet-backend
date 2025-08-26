package com.octagram.pollet.global.exception;

import com.octagram.pollet.global.status.BaseCode;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

	private final BaseCode errorCode;
	private Throwable cause;

	public BusinessException(BaseCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public BusinessException(BaseCode errorCode, Throwable cause) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
		this.cause = cause;
	}
}
