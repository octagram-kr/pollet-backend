package com.octagram.pollet.survey.presentation.dto.response;

public record QuestionOptionResponse(
	Long order,
	String content,
	String imageUrl,
	Boolean isCheckTarget,
	Boolean isCheckDiligent
) {
}
