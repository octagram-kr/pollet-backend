package com.octagram.pollet.board.presentation.dto.response;

import java.time.LocalDateTime;

public record BoardInPostFindAllResponse(
	LocalDateTime createdAt,
	String name,
	String description
) {
}
