package com.octagram.pollet.notification.domain.status;

import com.octagram.pollet.global.status.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum NotificationErrorCode implements BaseCode {

	NOTIFICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "NS_001", "알림을 찾을 수 없습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
