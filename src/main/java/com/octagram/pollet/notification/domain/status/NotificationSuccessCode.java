package com.octagram.pollet.notification.domain.status;

import com.octagram.pollet.global.status.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum NotificationSuccessCode implements BaseCode {

	READ_NOTIFICATION_LIST_SUCCESS(HttpStatus.OK, "NS_001", "알림 목록을 성공적으로 조회했습니다."),
	READ_NOTIFICATION_DETAIL_SUCCESS(HttpStatus.OK, "NS_002", "알림 상세를 성공적으로 조회했습니다."),
	CREATE_NOTIFICATION_SUCCESS(HttpStatus.CREATED, "NS_003", "알림을 성공적으로 생성했습니다."),
	UPDATE_NOTIFICATION_READ_SUCCESS(HttpStatus.OK, "NS_004", "알림을 성공적으로 읽음 처리했습니다."),
	DELETE_NOTIFICATION_SUCCESS(HttpStatus.OK, "NS_005", "알림을 성공적으로 삭제했습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
