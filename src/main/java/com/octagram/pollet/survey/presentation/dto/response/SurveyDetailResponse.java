package com.octagram.pollet.survey.presentation.dto.response;

import com.octagram.pollet.gifticon.domain.model.GifticonProduct;
import com.octagram.pollet.survey.domain.model.Survey;
import com.octagram.pollet.survey.domain.model.type.PrivacyType;
import com.octagram.pollet.survey.domain.model.type.RewardType;
import java.time.LocalDate;

public record SurveyDetailResponse(
        Long id,
        String title,
        String subtitle,
        String description,
        String SurveyImgUrl,
        LocalDate startDate,
        LocalDate endDate,
        int estimatedMinutes,
        int requiredResponseCount,
        int currentResponseCount,
        RewardType rewardType,
        int rewardPoint,
        GifticonProduct rewardGifticon,
        PrivacyType privacyType,
        Long privacyContents,
        Long privacyPurposeType,
        String privacyPurposeValue,
        LocalDate privacyExpireDate
) {
    public static SurveyDetailResponse from(Survey survey) {
        return new SurveyDetailResponse(
                survey.getId(),
                survey.getTitle(),
                survey.getSubtitle(),
                survey.getDescription(),
                survey.getCoverUrl(),
                survey.getStartDateTime().toLocalDate(),
                survey.getEndDateTime().toLocalDate(),
                survey.getEstimatedTime().intValue(),
                survey.getRequireResponseCount().intValue(),
                survey.getCurrentResponseCount().intValue(),
                survey.getRewardType(),
                survey.getRewardPoint() != null ? survey.getRewardPoint().intValue() : 0,
                survey.getGifticonProduct(),
                survey.getPrivacyType(),
                survey.getPrivacyContents(),
                survey.getPrivacyPurposeType(),
                survey.getPrivacyPurposeValue(),
                survey.getPrivacyExpireDate().toLocalDate()
        );
    }
}
