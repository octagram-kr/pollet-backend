package com.octagram.reward.member.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.octagram.reward.member.application.MemberService;
import com.octagram.reward.member.application.dto.request.CreateMemberRequest;
import com.octagram.reward.member.domain.Member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping
	public ResponseEntity<Member> createMember(@RequestBody @Valid CreateMemberRequest request) {
		Member member = memberService.createMember(request.nickname());
		return ResponseEntity.ok(member);
	}

	@GetMapping
	public ResponseEntity<Member> readMember(@RequestParam String nickname) {
		Member member = memberService.readMember(nickname);
		return ResponseEntity.ok(member);
	}
}
