package com.octagram.pollet.survey.presentation.dto.response;

public record QuestionOptionListResponse(
		Long id,
		String content,
		Boolean isCheckTarget
) {
}
