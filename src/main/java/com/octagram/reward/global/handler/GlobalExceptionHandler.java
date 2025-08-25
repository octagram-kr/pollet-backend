package com.octagram.reward.global.handler;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.octagram.reward.global.dto.ApiResponse;
import com.octagram.reward.global.dto.FieldErrorResponse;
import com.octagram.reward.global.exception.BusinessException;
import com.octagram.reward.global.status.BaseCode;
import com.octagram.reward.global.status.GlobalErrorCode;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<List<FieldErrorResponse>>> handleValidationException(
		MethodArgumentNotValidException ex
	) {
		BaseCode errorCode = GlobalErrorCode.REQUEST_BODY_INVALID;
		List<FieldErrorResponse> errors = ex.getBindingResult().getFieldErrors().stream()
			.map(err -> new FieldErrorResponse(err.getField(), err.getDefaultMessage()))
			.toList();
		return ResponseEntity
			.status(errorCode.getStatus())
			.body(ApiResponse.fail(errorCode, errors));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiResponse<?>> handleHttpMessageNotReadable(
		HttpMessageNotReadableException ex
	) {
		BaseCode errorCode = GlobalErrorCode.REQUEST_BODY_NOT_READABLE;
		return ResponseEntity
			.status(errorCode.getStatus())
			.body(ApiResponse.fail(errorCode));
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ApiResponse<FieldErrorResponse>> handleMissingServletRequestParameter(
		MissingServletRequestParameterException ex
	) {
		BaseCode errorCode = GlobalErrorCode.REQUEST_PARAM_REQUIRED;
		String fieldName = ex.getParameterName();
		String fieldMessage = String.format("필수 요청 파라미터 '%s'가 누락되었습니다.", fieldName);
		return ResponseEntity
			.status(errorCode.getStatus())
			.body(ApiResponse.fail(errorCode, new FieldErrorResponse(fieldName, fieldMessage)));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiResponse<FieldErrorResponse>> handleMethodArgumentTypeMismatch(
		MethodArgumentTypeMismatchException ex
	) {
		BaseCode errorCode = GlobalErrorCode.METHOD_PARAM_TYPE_INVALID;
		String fieldName = ex.getParameter().getParameterName();
		String fieldMessage = ex.getMessage();
		return ResponseEntity
			.status(errorCode.getStatus())
			.body(ApiResponse.fail(errorCode, new FieldErrorResponse(fieldName, fieldMessage)));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiResponse<List<FieldErrorResponse>>> handleConstraintViolation(
		ConstraintViolationException ex
	) {
		BaseCode errorCode = GlobalErrorCode.METHOD_PARAM_INVALID;
		List<FieldErrorResponse> errors = ex.getConstraintViolations().stream()
			.map(err -> new FieldErrorResponse(err.getPropertyPath().toString(), err.getMessage()))
			.toList();
		return ResponseEntity
			.status(errorCode.getStatus())
			.body(ApiResponse.fail(errorCode, errors));
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleNoHandlerFound(NoHandlerFoundException ex) {
		BaseCode errorCode = GlobalErrorCode.HANDLER_NOT_FOUND;
		return ResponseEntity
			.status(errorCode.getStatus())
			.body(ApiResponse.fail(errorCode, ex.getRequestURL()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> handleException(Exception ex) {
		BaseCode errorCode = GlobalErrorCode.UNEXPECTED_ERROR;
		return ResponseEntity
			.status(errorCode.getStatus())
			.body(ApiResponse.fail(errorCode));
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException ex) {
		ApiResponse<?> response = ApiResponse.fail(ex.getErrorCode(), ex.getCause());
		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}
}
