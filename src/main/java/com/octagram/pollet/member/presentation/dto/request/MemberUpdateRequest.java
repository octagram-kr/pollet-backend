package com.octagram.pollet.member.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MemberUpdateRequest(

	@NotBlank
	String nickname,

	@NotBlank
	String gender,

	@NotBlank
	String yearOfBirth,

	@NotBlank
	String job,

	@NotBlank
	String phoneNumber
) {
}
