package com.octagram.pollet.survey.presentation.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.octagram.pollet.survey.domain.model.type.EndCondition;
import com.octagram.pollet.survey.domain.model.type.PrivacyType;
import com.octagram.pollet.survey.domain.model.type.RewardType;

public record SurveyCreateRequest(
	String coverUrl,
	String title,
	String purpose,
	LocalDateTime startDateTime,
	LocalDateTime endDateTime,
	EndCondition endCondition,
	LocalDate submissionExpireDate,
	String description,
	PrivacyType privacyType,
	String privacyPurposeValue,
	Long privacyContents,
	LocalDate privacyExpireDate,
	String primaryColor,
	String secondaryColor,
	String targetGender,
	String targetAge,
	String targetJob,
	List<String> tags,
	List<SurveyCreateQuestionRequest> questions,
	RewardType rewardType,
	Long requireSubmissionCount,
	Long estimatedTime,
	Long rewardPoint,
	Long rewardGifticonProductId,
	Long rewardGifticonProductCount
) {
}
