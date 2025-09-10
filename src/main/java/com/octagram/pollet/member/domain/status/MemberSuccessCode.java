package com.octagram.pollet.member.domain.status;

import org.springframework.http.HttpStatus;

import com.octagram.pollet.global.status.BaseCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseCode {

	MEMBER_UPDATE_SUCCESS(HttpStatus.OK, "MS_001", "회원 정보가 성공적으로 변경되었습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
