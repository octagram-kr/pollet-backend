package com.octagram.pollet.notification.presentation;

import com.octagram.pollet.global.dto.ApiResponse;
import com.octagram.pollet.notification.application.NotificationService;
import com.octagram.pollet.notification.application.SseMitterService;
import com.octagram.pollet.notification.domain.status.NotificationSuccessCode;
import com.octagram.pollet.notification.presentation.dto.response.NotificationResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

	private final NotificationService notificationService;
	private final SseMitterService sseMitterService;

	@GetMapping("/subscribe")
	@Operation(summary = "실시간 알림 구독", description =" 유저가 알림을 구독합니다. 연결이 유지되는 동안 서버에서 발생하는 알림 이벤트가 스트리밍 방식으로 전달됩니다." )
	public SseEmitter subscribeNotifications(@AuthenticationPrincipal String memberId) {
		return sseMitterService.createEmitter(memberId);
	}

	@GetMapping
	@Operation(summary = "카테고리별 수신한 알림 내역 조회", description ="로그인한 사용자가 수신한 알림 내역을 카테고리별로 조회합니다.")
	public ApiResponse<Slice<NotificationResponse>> getNotificationsByCategory(
			@AuthenticationPrincipal String memberId,
			@RequestParam(defaultValue = "survey") String mode,
			Pageable pageable
	) {
		Slice<NotificationResponse> notifications = notificationService.getNotificationsByCategory(memberId, mode, pageable);
		return ApiResponse.success(NotificationSuccessCode.READ_NOTIFICATION_LIST_SUCCESS, notifications);
	}

	@GetMapping("/{notificationId}")
	@Operation(summary = "알림 내역 상세 조회", description = "특정 알림의 상세 정보를 조회합니다.")
	public ApiResponse<NotificationResponse> getNotification(
			@AuthenticationPrincipal String memberId,
			@PathVariable Long notificationId
	) {
		NotificationResponse notification = notificationService.getNotificationDetail(memberId, notificationId);
		return ApiResponse.success(NotificationSuccessCode.READ_NOTIFICATION_DETAIL_SUCCESS, notification);
	}

}
