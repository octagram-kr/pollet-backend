package com.octagram.pollet.survey.presentation.dto.response.standard;

import com.octagram.pollet.survey.domain.model.type.TagType;

public record TagResponse(
	Long id,
	String name,
	TagType type
) {
}
