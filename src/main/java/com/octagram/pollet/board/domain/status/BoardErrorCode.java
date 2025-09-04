package com.octagram.pollet.board.domain.status;

import org.springframework.http.HttpStatus;

import com.octagram.pollet.global.status.BaseCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardErrorCode implements BaseCode {

	BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "BE_001", "게시판을 찾을 수 없습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
