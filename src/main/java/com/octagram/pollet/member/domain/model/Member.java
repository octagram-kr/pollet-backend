package com.octagram.pollet.member.domain.model;

import java.time.LocalDateTime;

import com.octagram.pollet.global.domain.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Table(name = "member")
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AuthProvider authProvider;

	@Column(nullable = false)
	private String profileImageUrl;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	@Column(nullable = false)
	private String memberId;

	@Column(nullable = false)
	private String email;

	@Column(unique = true)
	private String nickname;

	private String job;

	private String gender;

	private String yearOfBirth;

	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@Builder.Default
	private MemberStatus status = MemberStatus.ACTIVE;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Rank rank;

	private LocalDateTime attendedAt;

	@Column(nullable = false)
	private Long attendanceStreak;

	@Column(nullable = false)
	@Builder.Default
	private Boolean is_deleted = false;
}
