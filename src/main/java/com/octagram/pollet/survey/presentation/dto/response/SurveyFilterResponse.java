package com.octagram.pollet.survey.presentation.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.octagram.pollet.gifticon.presentation.dto.response.GifticonProductInSurveyFilterResponse;

public record SurveyFilterResponse(
	String title,
	String coverUrl,
	LocalDateTime endDateTime,
	Long estimatedTime,
	Long rewardPoint,
	GifticonProductInSurveyFilterResponse rewardGifticonProduct,
	Long requireResponseCount,
	Long currentResponseCount,
	List<TagInSurveyFilterResponse> tags
) {
}
