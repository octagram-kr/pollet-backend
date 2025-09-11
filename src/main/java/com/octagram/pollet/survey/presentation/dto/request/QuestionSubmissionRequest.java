package com.octagram.pollet.survey.presentation.dto.request;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;

import com.octagram.pollet.survey.domain.model.Question;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@ParameterObject
@Schema(name = "QuestionSubmissionRequest", description = "문항 별 응답 DTO")
public record QuestionSubmissionRequest(

	@Schema(description = "문항 ID")
	Long questionId,

	@Schema(description = "답변 (서술형일 경우만 작성)")
	String answer,

	@ArraySchema(
		arraySchema = @Schema(description = "문항 별 선택지 응답 (객관식일 경우만 작성, 복수 응답 가능)"),
		schema = @Schema(implementation = QuestionOptionSubmissionRequest.class)
	)
	List<QuestionOptionSubmissionRequest> questionOptionSubmissions
) {
}
