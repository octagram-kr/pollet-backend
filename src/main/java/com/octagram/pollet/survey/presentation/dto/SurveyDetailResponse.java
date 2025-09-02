package com.octagram.pollet.survey.presentation.dto;

import com.octagram.pollet.gifticon.domain.model.GifticonProduct;
import com.octagram.pollet.survey.domain.model.Survey;
import com.octagram.pollet.survey.domain.model.type.PrivacyType;
import com.octagram.pollet.survey.domain.model.type.RewardType;
import com.octagram.pollet.survey.presentation.dto.response.TagResponse;

import java.time.LocalDate;
import java.util.List;

public record SurveyDetailResponse(
        Long id,
        String title,
        String subtitle,
        String description,
        String SurveyImgUrl,
//        LocalDate startDate,
//        LocalDate endDate,
        // 참여 예상 시간
        int estimatedMinutes,
        // 참여 현황
        int requiredResponseCount,
        int currentResponseCount,
        // 리워드(포인트 or 기프티콘)
        RewardType rewardType,
        int rewardPoint, // null 가능
        GifticonProduct rewardGifticon, // null 가능

        // 개인정보 처리
        PrivacyType privacyType,
        Long privacyContents,        // 정의된 코드(항목 집합) 식별자
        Long privacyPurposeType,     // 정의된 코드(목적) 식별자
        String privacyPurposeValue,  // 목적 상세(자유기입)

        // 응답 데이터 보관 만료
        LocalDate privacyExpireDate,
//        설문 기간 (엔티티 필드 추가 필요)

        // 태그 정보 추가
        List<TagResponse> tags
) {
    // 팩토리 메서드 추가
    public static SurveyDetailResponse from(Survey survey, List<TagResponse> tags) {
        return new SurveyDetailResponse(
                survey.getId(),
                survey.getTitle(),
                survey.getSubtitle(),
                survey.getDescription(),
                survey.getCoverUrl(),
//                survey.getStartDate(),
//                survey.getEndDate(),
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
