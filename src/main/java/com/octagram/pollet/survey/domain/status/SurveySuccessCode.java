package com.octagram.pollet.survey.domain.status;

import com.octagram.pollet.global.status.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SurveySuccessCode implements BaseCode {

	READ_TAGS_SUCCESS(HttpStatus.OK, "SS_001", "전체 태그 목록을 성공적으로 조회했습니다."),
	READ_SURVEY_TAGS_SUCCESS(HttpStatus.OK, "SS_002", "설문조사에 등록된 태그들을 성공적으로 조회했습니다."),
	READ_SURVEY_DETAIL_SUCCESS(HttpStatus.OK, "SS_003", "설문조사 상세 정보를 성공적으로 조회했습니다."),
	READ_SURVEY_COUNT_SUCCESS(HttpStatus.OK, "SS_004", "전체 설문조사의 수를 성공적으로 조회했습니다."),
	SEARCH_SURVEYS_SUCCESS(HttpStatus.OK, "SS_005", "설문조사를 성공적으로 조회했습니다."),
	READ_RECENT_SURVEYS_SUCCESS(HttpStatus.OK, "SS_006", "최근 등록된 설문조사 목록을 성공적으로 조회했습니다."),
	READ_RECENT_REPRESENTATIVE_QUESTIONS_SUCCESS(HttpStatus.OK, "SS_007", "최근 등록된 설문조사의 대표 질문을 성공적으로 조회했습니다."),
	CREATE_SURVEY_SUBMISSION_SUCCESS(HttpStatus.OK, "SS_008", "설문조사 응답 제출을 성공적으로 완료했습니다."),
	CREATE_SURVEY_SUCCESS(HttpStatus.OK, "SS_009", "설문조사를 성공적으로 생성했습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
