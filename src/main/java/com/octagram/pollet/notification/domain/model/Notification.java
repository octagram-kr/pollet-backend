package com.octagram.pollet.notification.domain.model;

import com.octagram.pollet.global.domain.model.BaseEntity;
import com.octagram.pollet.member.domain.model.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "notification")
public class Notification extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver_id", nullable = false)
	private Member receiver;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_id", nullable = true)
	private Member sender;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String message;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private NotificationType notificationType;

	@Builder.Default
	@Column(nullable = false)
	private boolean isRead = false;

	public void markAsRead() {
		if (!this.isRead) {
			this.isRead = true;
			// 추후 필요시 readAt 추가
			// this.readAt = LocalDateTime.now();
		}
	}
}
