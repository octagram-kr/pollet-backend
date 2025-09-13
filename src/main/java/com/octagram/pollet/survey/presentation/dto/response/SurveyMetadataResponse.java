package com.octagram.pollet.survey.presentation.dto.response;

import java.time.LocalDateTime;

public record SurveyMetadataResponse(
		String title,
		String subtitle,
		String description,
		String creatorName,
		LocalDateTime startDateTime,
		LocalDateTime endDateTime,
		Long requireSubmissionCount,
		Long currentSubmissionCount,
		String status // 진행중 / 종료 등
) {
}
