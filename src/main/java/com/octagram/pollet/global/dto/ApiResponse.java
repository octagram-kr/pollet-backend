package com.octagram.pollet.global.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.octagram.pollet.global.status.BaseCode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

	@JsonIgnore
	private final HttpStatus status;

	private final boolean success;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final String code;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;

	public static <T> ApiResponse<T> success(BaseCode code) {
		return new ApiResponse<>(code.getStatus(), true, code.getCode(), code.getMessage(), null);
	}

	public static <T> ApiResponse<T> success(BaseCode code, T data) {
		return new ApiResponse<>(code.getStatus(), true, code.getCode(), code.getMessage(), data);
	}

	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(HttpStatus.OK, true, null, null, data);
	}

	public static <T> ApiResponse<T> fail(BaseCode code) {
		return new ApiResponse<>(code.getStatus(), false, code.getCode(), code.getMessage(), null);
	}

	public static <T> ApiResponse<T> fail(BaseCode code, T data) {
		return new ApiResponse<>(code.getStatus(), false, code.getCode(), code.getMessage(), data);
	}
}
