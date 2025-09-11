package com.octagram.pollet.survey.presentation.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ParticipantResultResponse(
		String surveyTitle,
		String respondentNickname,
		LocalDateTime submittedAt,
		List<QuestionAnswer> answers
) {
	public record QuestionAnswer(
			Long questionId,
			String questionTitle,
			String questionType,
			List<SelectedOption> selectedOptions, // 객관식 응답
			String subjectiveAnswer              // 주관식 응답
	) {}

	public record SelectedOption(
			Long optionId,
			String optionText
	) {}
}