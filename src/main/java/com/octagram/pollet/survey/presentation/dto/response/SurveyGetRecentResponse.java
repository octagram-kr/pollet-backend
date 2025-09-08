package com.octagram.pollet.survey.presentation.dto.response;

public record SurveyGetRecentResponse(
		Long id,
		String title,
		String coverUrl,
		String gifticonName,
		String rewardPoint,
		String estimatedTime
) {
}
