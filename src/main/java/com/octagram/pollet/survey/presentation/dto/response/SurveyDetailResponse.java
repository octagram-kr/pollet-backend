package com.octagram.pollet.survey.presentation.dto.response;

import com.octagram.pollet.gifticon.domain.model.GifticonProduct;
import com.octagram.pollet.survey.domain.model.Survey;
import com.octagram.pollet.survey.domain.model.type.PrivacyType;
import com.octagram.pollet.survey.domain.model.type.RewardType;
import java.time.LocalDate;
import java.util.List;

public record SurveyDetailResponse(
        Long id,
        String title,
        String subtitle,
        String description,
        String SurveyImgUrl,
        // 설문기간
        LocalDate startDate,
        LocalDate endDate,
        // 참여 예상 시간
        int estimatedMinutes,
        // 참여 현황
        int requiredResponseCount,
        int currentResponseCount,
        // 리워드(포인트 or 기프티콘)
        RewardType rewardType,
        int rewardPoint,
        GifticonProduct rewardGifticon,
        // 개인정보 처리
        PrivacyType privacyType,
        Long privacyContents,
        Long privacyPurposeType,
        String privacyPurposeValue,
        // 응답 데이터 보관 만료
        LocalDate privacyExpireDate,
        // 태그 정보 추가
        List<TagResponse> tags
) {
    public static SurveyDetailResponse from(Survey survey, List<TagResponse> tags) {
        return new SurveyDetailResponse(
                survey.getId(),
                survey.getTitle(),
                survey.getSubtitle(),
                survey.getDescription(),
                survey.getCoverUrl(),
                survey.getStartDate(),
                survey.getEndDate(),
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
                survey.getPrivacyExpireDate().toLocalDate(),
                tags
        );
    }
}
