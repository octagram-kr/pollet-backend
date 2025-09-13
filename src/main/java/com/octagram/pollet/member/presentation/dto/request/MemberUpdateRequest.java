package com.octagram.pollet.member.presentation.dto.request;

import org.springdoc.core.annotations.ParameterObject;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@ParameterObject
@Schema(description = "회원 정보 수정 DTO")
public record MemberUpdateRequest(

	@Schema(description = "닉네임")
	@NotBlank
	String nickname,

	@Schema(description = "성별")
	@NotBlank
	String gender,

	@Schema(description = "출생연도")
	@NotBlank
	String yearOfBirth,

	@Schema(description = "직업")
	@NotBlank
	String job,

	@Schema(description = "전화번호 (- 제외)")
	@NotBlank
	String phoneNumber
) {
}
