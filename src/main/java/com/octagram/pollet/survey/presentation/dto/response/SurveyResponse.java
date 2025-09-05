package com.octagram.pollet.survey.presentation.dto.response;

import com.octagram.pollet.survey.domain.model.Survey;

public record SurveyResponse(
        Long id,
        String title,
        Long rewardPoint,
        Long estimatedTime
) {
    public static SurveyResponse from(Survey survey) {
        return new SurveyResponse(
                survey.getId(),
                survey.getTitle(),
                survey.getRewardPoint(),
                survey.getEstimatedTime()
        );
    }
}

