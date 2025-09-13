package com.octagram.pollet.survey.presentation.dto.request;

public record SurveyCreateQuestionOptionRequest(
	Long order,
	String content,
	String imageUrl,
	Boolean isCheckTarget,
	Boolean isCheckDiligent
) {
}
