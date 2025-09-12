package com.octagram.pollet.survey.domain.status;

import org.springframework.http.HttpStatus;

import com.octagram.pollet.global.status.BaseCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SurveyErrorCode implements BaseCode {

	TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "SE_001", "요청한 태그가 존재하지 않습니다."),
	DUPLICATE_SURVEY_TAG(HttpStatus.CONFLICT, "SE_002", "이미 설문조사에 등록된 태그입니다."),
	INVALID_TAG(HttpStatus.BAD_REQUEST, "SE_003", "유효하지 않은 태그입니다."),
	TAG_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "SE_004", "설문조사에 등록 가능한 태그 개수를 초과했습니다."),
	SURVEY_NOT_FOUND(HttpStatus.NOT_FOUND, "SE_005", "요청한 설문이 존재하지 않습니다."),
	INVALID_ACCESS(HttpStatus.FORBIDDEN, "SE_006", "잘못된 접근입니다."),
	QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "SE_007", "설문조사의 대표 질문이 존재하지 않습니다."),
	SUBMISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "SE_008", "요청한 제출 내역이 존재하지 않습니다."),
	INVALID_SUBMISSION(HttpStatus.BAD_REQUEST, "SE_009", "제출 내역이 해당 설문과 일치하지 않습니다."),
	UNAUTHORIZED_ACCESS(HttpStatus.FORBIDDEN, "SE_010", "설문 생성자가 아닙니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
