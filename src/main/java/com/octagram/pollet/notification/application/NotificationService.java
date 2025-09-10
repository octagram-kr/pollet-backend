package com.octagram.pollet.notification.application;

import com.octagram.pollet.global.exception.BusinessException;
import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.member.domain.repository.MemberRepository;
import com.octagram.pollet.member.domain.status.MemberErrorCode;
import com.octagram.pollet.notification.application.mapper.NotificationMapper;
import com.octagram.pollet.notification.domain.model.Notification;
import com.octagram.pollet.notification.domain.model.NotificationType;
import com.octagram.pollet.notification.domain.repository.NotificationRepository;
import com.octagram.pollet.notification.domain.status.NotificationErrorCode;
import com.octagram.pollet.notification.presentation.dto.response.NotificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.octagram.pollet.member.domain.model.QMember.member;
import static com.octagram.pollet.notification.domain.model.QNotification.notification;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

	private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();
	private final ExecutorService executor = Executors.newSingleThreadExecutor();
	private final NotificationRepository notificationRepository;
	private final MemberRepository memberRepository;
	private final NotificationMapper notificationMapper;

	// 알림을 생성하고 저장한 뒤, 실시간 전송 기능을 trigger 하는 역할
	@Transactional
	public void sendNotification(String receiverId, NotificationType type, String title, String content) {
		// receiverId로 Member 객체 조회
		Member receiver = memberRepository.findById(Long.valueOf(receiverId))
				.orElseThrow(() -> new BusinessException(MemberErrorCode.MEMBER_NOT_FOUND));

		// Notification 객체 생성
		Notification notification = Notification.builder()
				.receiver(receiver)
				.title(title)
				.message(content)
				.notificationType(type)
				.isRead(false)
				.build();

		// DB에 알림 저장
		Notification savedNotification = notificationRepository.save(notification);
		// 저장된 알림 객체를 이용하여 실시간 전송 메서드 호출
		sendRealTimeNotification(savedNotification);
	}

	// 위에서 지정된 알림 객체를 실시간으로 지정된 클라이언트에게 알림을 전송
	@Transactional
	public void sendRealTimeNotification(Notification notification) {
		// SseEmiter 조회 : 특정 사용자와 연결된 클라이언트(SSE Stream)가 존재하는지 확인
		SseEmitter emitter = emitters.get(notification.getReceiver().getId());
		// 연결이 존재하는 경우
		if (emitter != null) {
			executor.execute(() -> {
				try {
					// 알림 타입에 따라 이벤트 이름 변경
					String eventName = notification.getNotificationType().name().toLowerCase(); // "interview" 또는 "survey"
					// SseEmitter를 통해 알림 전송
					emitter.send(SseEmitter.event().name(eventName).data(notification.getMessage())); // 클라이언트는 전송된 데이터를 실시간으로 수신
				} catch (Exception e) {
					// 알림 전송이 실패할 경우 emitter 제거 및 로그 기록
					emitters.remove(notification.getReceiver().getId()); // emitter 제거
					log.error("Failed to send notification", e); // 로그 남기기
				}
			});
		}
	}


	@Transactional(readOnly = true)
	public Slice<NotificationResponse> getNotificationsByCategory(String memberId, String mode, Pageable pageable) {
		Member receiver = memberRepository.findById(Long.valueOf(memberId))
				.orElseThrow(() -> new BusinessException(MemberErrorCode.MEMBER_NOT_FOUND));

		NotificationType type = mapModeToType(mode);
		Slice<Notification> slice = notificationRepository.findByReceiverAndNotificationTypeOrderByCreatedAtDesc(receiver, type, pageable);

		return slice.map(notificationMapper::toNotificationResponse);
	}

	private NotificationType mapModeToType(String mode) {
		return switch (mode.toLowerCase()) {
			case "interview" -> NotificationType.INTERVIEW;
			case "survey" -> NotificationType.SURVEY;
			default -> NotificationType.SURVEY;
		};
	}

	@Transactional(readOnly = true)
	public NotificationResponse getNotificationDetail(String memberId, Long notificationId) {
		Notification notification = notificationRepository.findById(notificationId)
				.orElseThrow(() -> new BusinessException(NotificationErrorCode.NOTIFICATION_NOT_FOUND));

		// 권한 체크: 본인 알림만 조회 가능
		if (!notification.getReceiver().getId().equals(memberId)) {
			throw new BusinessException(MemberErrorCode.UNAUTHORIZED_ACCESS);
		}

		return notificationMapper.toNotificationResponse(notification);
	}

}
