package com.octagram.pollet.member.presentation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.octagram.pollet.global.dto.ApiResponse;
import com.octagram.pollet.member.application.MemberService;
import com.octagram.pollet.member.domain.status.MemberSuccessCode;
import com.octagram.pollet.member.presentation.dto.request.MemberUpdateRequest;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PutMapping("/update")
	@Operation(summary = "회원 정보 수정", description = "최초 로그인 후 추가 정보를 입력하거나 회원 정보를 수정합니다.")
	public ApiResponse<Void> updateMember(@AuthenticationPrincipal String memberId, @RequestBody @Valid MemberUpdateRequest request) {
		memberService.updateMember(memberId, request);
		return ApiResponse.success(MemberSuccessCode.MEMBER_UPDATE_SUCCESS);
	}
}
