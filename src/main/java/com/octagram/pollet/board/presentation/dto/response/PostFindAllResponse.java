package com.octagram.pollet.board.presentation.dto.response;

import com.octagram.pollet.board.domain.model.type.PostType;

public record PostFindAllResponse(
	Long id,
	BoardInPostFindAllResponse board,
	String title,
	String content,
	PostType postType
) {
}
