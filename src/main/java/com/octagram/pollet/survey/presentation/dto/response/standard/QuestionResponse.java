package com.octagram.pollet.survey.presentation.dto.response.standard;

import java.util.List;

import com.octagram.pollet.survey.domain.model.type.QuestionType;

public record QuestionResponse(
	Long id,
	String title,
	String description,
	QuestionType questionType,
	String imageUrl,
	Long page,
	Long order,
	Boolean isRequired,
	Boolean isCheckTarget,
	Boolean isCheckDiligent,
	QuestionType type,
	List<QuestionOptionResponse> options
) {
}
