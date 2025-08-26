package com.octagram.pollet.sample.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateSampleRequest(
	@NotBlank(message = "값은 필수입니다.")
	String value
) {
}
