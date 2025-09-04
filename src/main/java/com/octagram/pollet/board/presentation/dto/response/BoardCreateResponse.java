package com.octagram.pollet.board.presentation.dto.response;

public record BoardCreateResponse(
	Long id,
	String name,
	String description
) {
}
