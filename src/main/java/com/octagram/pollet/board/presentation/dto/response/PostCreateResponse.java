package com.octagram.pollet.board.presentation.dto.response;

import com.octagram.pollet.board.domain.model.type.PostType;

public record PostCreateResponse(
	Long id,
	String title,
	String content,
	PostType postType
) {
}
