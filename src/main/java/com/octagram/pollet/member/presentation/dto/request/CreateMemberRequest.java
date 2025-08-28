package com.octagram.pollet.member.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateMemberRequest(
	@NotNull(message = "닉네임은 필수입니다.")
	String nickname
) {
}
