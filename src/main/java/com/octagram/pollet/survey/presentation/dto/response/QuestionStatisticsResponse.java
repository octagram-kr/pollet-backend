package com.octagram.pollet.survey.presentation.dto.response;

import java.util.List;

public record QuestionStatisticsResponse(
		Long surveyId,
		String surveyTitle,
		int respondentCount,
		List<QuestionResult> questions
) {
	public record QuestionResult(
			Long questionId,
			String questionTitle,
			String questionType,
			int answeredCount,
			List<OptionResult> options,
			List<String> etcAnswers
	) {}

	public record OptionResult(
			Long optionId,
			String optionText,
			int responseCount,
			double responseRatio
	) {}
}
