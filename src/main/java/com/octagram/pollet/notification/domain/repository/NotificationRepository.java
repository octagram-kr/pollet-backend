package com.octagram.pollet.notification.domain.repository;

import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.notification.domain.model.Notification;
import com.octagram.pollet.notification.domain.model.NotificationType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	Slice<Notification> findByReceiverAndNotificationTypeOrderByCreatedAtDesc(
			Member receiver,
			NotificationType notificationType,
			Pageable pageable);
}
