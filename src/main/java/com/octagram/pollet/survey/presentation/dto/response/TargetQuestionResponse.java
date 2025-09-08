package com.octagram.pollet.survey.presentation.dto.response;

import java.util.List;

public record TargetQuestionResponse(
		Long id,
		String title,
		List<QuestionOptionListResponse> questionOptions
) {
}
