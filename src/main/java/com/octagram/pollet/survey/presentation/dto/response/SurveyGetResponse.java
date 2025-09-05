package com.octagram.pollet.survey.presentation.dto.response;

public record SurveyGetResponse(
	Long id,
	String title,
	Long rewardPoint,
	Long estimatedTime
) {
}
