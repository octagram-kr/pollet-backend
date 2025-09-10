package com.octagram.pollet.member.domain.status;

import com.octagram.pollet.global.status.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseCode {

	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "ME_001", "요청한 회원이 존재하지 않습니다."),
	DUPLICATE_MEMBER(HttpStatus.CONFLICT, "ME_002", "이미 존재하는 회원입니다."),
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "ME_003", "아이디 또는 비밀번호가 올바르지 않습니다."),
	UNAUTHORIZED_ACCESS(HttpStatus.FORBIDDEN, "ME_004", "해당 리소스에 접근할 권한이 없습니다."),
	MEMBER_STATUS_BLOCKED(HttpStatus.FORBIDDEN, "ME_005", "차단된 회원입니다."),
	MEMBER_STATUS_INACTIVE(HttpStatus.BAD_REQUEST, "ME_006", "비활성화된 회원입니다."),
	OAUTH2_REGISTRATION_FAILED(HttpStatus.BAD_REQUEST, "ME_007", "소셜 로그인 회원 등록에 실패했습니다."),
	INVALID_MEMBER_REQUEST(HttpStatus.BAD_REQUEST, "ME_008", "잘못된 회원 요청입니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
