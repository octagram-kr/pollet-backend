package com.octagram.pollet.member.domain.status;

import org.springframework.http.HttpStatus;

import com.octagram.pollet.global.domain.status.BaseCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseCode {

	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "ME_001", "회원 정보를 찾을 수 없습니다"),
	DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "ME_002" , "닉네임은 중복될 수 없습니다."),
	POINT_NOT_FOUND(HttpStatus.NOT_FOUND, "ME_003", "포인트 정보를 찾을 수 없습니다."),
	BALANCE_NOT_ENOUGH(HttpStatus.CONFLICT, "ME_003", "포인트 잔고가 부족합니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
