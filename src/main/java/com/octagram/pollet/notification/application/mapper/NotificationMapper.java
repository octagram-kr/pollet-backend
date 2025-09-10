package com.octagram.pollet.notification.application.mapper;

import com.octagram.pollet.notification.domain.model.Notification;
import com.octagram.pollet.notification.presentation.dto.response.NotificationResponse;
import org.mapstruct.Mapper;

@Mapper
public interface NotificationMapper {

	NotificationResponse toNotificationResponse(Notification notification);
}
