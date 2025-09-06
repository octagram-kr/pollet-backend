package com.octagram.pollet.survey.presentation.dto.response;

public record SurveyGetRecentResponse(
		Long id,
		String title,
		String coverImgUrl,
		String gifticonName,
		String rewardPoint,
		String estimatedTime
		// 조사 질문 필드 추가 예정
) {
}
