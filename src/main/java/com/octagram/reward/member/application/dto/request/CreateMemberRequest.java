package com.octagram.reward.member.application.dto.request;

import jakarta.validation.constraints.NotNull;

public record addMemberRequest(

	@NotNull(message = "닉네임은 필수입니다.")
	String nickname
) {
}
