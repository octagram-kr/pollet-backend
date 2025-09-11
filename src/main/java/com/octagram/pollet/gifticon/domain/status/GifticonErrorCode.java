package com.octagram.pollet.gifticon.domain.status;

import org.springframework.http.HttpStatus;

import com.octagram.pollet.global.status.BaseCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GifticonErrorCode implements BaseCode {

	GIFTICON_PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "GPE_001", "기프티콘 상품이 존재하지 않습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
