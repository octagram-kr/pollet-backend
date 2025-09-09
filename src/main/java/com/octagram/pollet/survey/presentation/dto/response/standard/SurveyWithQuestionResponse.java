package com.octagram.pollet.survey.presentation.dto.response.standard;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.octagram.pollet.gifticon.presentation.dto.response.GifticonProductResponse;
import com.octagram.pollet.survey.domain.model.type.EndCondition;
import com.octagram.pollet.survey.domain.model.type.PrivacyType;
import com.octagram.pollet.survey.domain.model.type.RewardType;
import com.octagram.pollet.survey.domain.model.type.TargetGender;

public record SurveyWithQuestionResponse(
	Long id,
	String title,
	String subtitle,
	String description,
	String coverUrl,
	String primaryColor,
	String secondaryColor,
	String creatorName,
	String purpose,
	LocalDateTime startDateTime,
	EndCondition endCondition,
	LocalDateTime endDateTime,
	Boolean isTemplate,
	TargetGender targetGender,
	LocalDate submissionExpireDate,
	PrivacyType privacyType,
	Long privacyContents,
	Long privacyPurposeType,
	String privacyPurposeValue,
	LocalDate privacyExpireDate,
	Long estimatedTime,
	Long requireSubmissionCount,
	Long currentSubmissionCount,
	RewardType rewardType,
	Long rewardPoint,
	Long availablePoint,
	GifticonProductResponse gifticonProduct,
	List<TagResponse> surveyTags,
	List<QuestionResponse> questions,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {
}
