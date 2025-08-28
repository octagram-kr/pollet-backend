package com.octagram.pollet.member.domain.model;

import java.time.LocalDateTime;

import com.octagram.pollet.global.domain.model.BaseEntity;
import com.octagram.pollet.member.domain.model.type.AuthProvider;
import com.octagram.pollet.member.domain.model.type.MemberGrade;
import com.octagram.pollet.member.domain.model.type.MemberStatus;
import com.octagram.pollet.member.domain.type.Rank;
import com.octagram.pollet.member.domain.model.type.Role;

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

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AuthProvider authProvider;

	@Column(nullable = false)
	private String profileImageUrl;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(nullable = false, unique = true)
	private String memberId;

	@Column(nullable = false)
	private String email;

	@Column(nullable = true, unique = true)
	private String nickname;

	@Column(nullable = true)
	private String job;

	@Column(nullable = true)
	private String gender;

	@Column(nullable = true)
	private String yearOfBirth;

	@Column(nullable = true)
	private String phoneNumber;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@Builder.Default
	private MemberStatus status = MemberStatus.ACTIVE;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MemberGrade rank;

	@Column(nullable = true)
	private LocalDateTime attendedAt;

	@Column(nullable = false)
	private Long attendanceStreak;

	@Column(nullable = false)
	@Builder.Default
	private Boolean isDeleted = false;
}
