package com.octagram.pollet.notification.presentation.dto.response;

import com.octagram.pollet.notification.domain.model.NotificationType;
import java.time.LocalDateTime;

public record NotificationResponse(
		Long id,
		String title,
		String message,
		NotificationType type,
		boolean isRead,
		LocalDateTime createdAt
) {
}
