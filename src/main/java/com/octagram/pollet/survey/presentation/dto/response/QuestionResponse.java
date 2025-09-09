package com.octagram.pollet.survey.presentation.dto.response;

import java.util.List;

import com.octagram.pollet.survey.domain.model.type.QuestionType;

public record QuestionResponse(
	Long order,
	Long page,
	String title,
	String description,
	QuestionType questionType,
	Boolean isRequired,
	Boolean isCheckTarget,
	Boolean isCheckDiligent,
	String imageUrl,
	List<QuestionOptionResponse> options
) {
}
