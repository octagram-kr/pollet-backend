package com.octagram.pollet.survey.presentation.dto.request;

import org.springdoc.core.annotations.ParameterObject;

import io.swagger.v3.oas.annotations.media.Schema;

@ParameterObject
@Schema(name = "QuestionOptionSubmissionRequest", description = "문항 별 선택지 응답 DTO")
public record QuestionOptionSubmissionRequest(

	@Schema(description = "문항 선택지 ID")
	Long questionOptionId
) {
}
