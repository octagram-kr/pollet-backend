package com.octagram.reward.global.status;

import org.springframework.http.HttpStatus;

public interface BaseCode {
	HttpStatus getStatus();
	String getCode();
	String getMessage();
}
