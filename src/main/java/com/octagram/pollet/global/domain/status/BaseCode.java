package com.octagram.pollet.global.domain.status;

import org.springframework.http.HttpStatus;

public interface BaseCode {
	HttpStatus getStatus();
	String getCode();
	String getMessage();
}
