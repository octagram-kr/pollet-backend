package com.octagram.pollet.survey.presentation.dto.request;

import java.util.List;

import com.octagram.pollet.survey.domain.model.type.QuestionType;

public record SurveyCreateQuestionRequest(
	Long page,
	Long order,
	String title,
	String description,
	QuestionType type,
	Boolean isRequired,
	Boolean isCheckTarget,
	Boolean isCheckDiligent,
	String imageUrl,
	List<SurveyCreateQuestionOptionRequest> options
) {
}
