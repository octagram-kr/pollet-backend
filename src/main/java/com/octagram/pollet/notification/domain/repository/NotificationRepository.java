package com.octagram.pollet.notification.domain.repository;

import com.octagram.pollet.notification.domain.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
