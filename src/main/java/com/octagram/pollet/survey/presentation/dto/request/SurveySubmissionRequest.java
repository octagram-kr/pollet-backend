package com.octagram.pollet.survey.presentation.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import org.springdoc.core.annotations.ParameterObject;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@ParameterObject
@Schema(name = "SurveySubmissionRequest", description = "설문 응답 제출 DTO")
public record SurveySubmissionRequest(

	@Schema(description = "설문 응답 시작 시간")
	LocalDateTime startedAt,

	@Schema(description = "설문 응답 종료 시간")
	LocalDateTime completedAt,

	@ArraySchema(
		arraySchema = @Schema(description = "문항 별 응답"),
		schema = @Schema(implementation = QuestionSubmissionRequest.class)
	)
	List<QuestionSubmissionRequest> questionSubmissions
) {
}
