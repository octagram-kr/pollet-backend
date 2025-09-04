package com.octagram.pollet.board.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

public record BoardCreateRequest(

	@NotNull
	String name,

	@NotNull
	String description
) {
}
