package com.octagram.pollet.survey.presentation.dto.response;

import com.octagram.pollet.gifticon.domain.model.GifticonProduct;
import com.octagram.pollet.survey.domain.model.type.PrivacyType;
import com.octagram.pollet.survey.domain.model.type.RewardType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SurveyGetDetailResponse(
	Long id,
	GifticonProduct gifticonProduct,
	String title,
	String subtitle,
	String description,
	String coverUrl,
	LocalDateTime startDateTime,
	LocalDateTime endDateTime,
	PrivacyType privacyType,
	Long privacyContents,
	Long privacyPurposeType,
	String privacyPurposeValue,
	LocalDate privacyExpireDate,
	Long estimatedTime,
	Long requireResponseCount,
	Long currentResponseCount,
	RewardType rewardType,
	Long rewardPoint
) {
}
