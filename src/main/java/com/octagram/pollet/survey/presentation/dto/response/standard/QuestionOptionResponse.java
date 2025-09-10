package com.octagram.pollet.survey.presentation.dto.response.standard;

public record QuestionOptionResponse(
	Long id,
	Long order,
	String content,
	String imageUrl,
	Boolean isCheckTarget,
	Boolean isCheckDiligent
) {
}
