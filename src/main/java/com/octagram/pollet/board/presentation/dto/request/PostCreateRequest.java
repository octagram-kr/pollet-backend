package com.octagram.pollet.board.presentation.dto.request;

import com.octagram.pollet.board.domain.model.type.PostType;

import jakarta.validation.constraints.NotNull;

public record PostCreateRequest(

	@NotNull
	String title,

	@NotNull
	String content,

	@NotNull
	PostType postType
) {
}
