package com.octagram.pollet.survey.domain.status;

import com.octagram.pollet.global.status.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SurveySuccessCode implements BaseCode {

    READ_TAGS_SUCCESS(HttpStatus.OK, "SS_001", "전체 태그 목록을 성공적으로 조회했습니다."),
    READ_SURVEY_TAGS_SUCCESS(HttpStatus.OK, "SS_002", "설문조사에 등록된 태그들을 성공적으로 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

