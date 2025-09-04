package com.octagram.pollet.survey.domain.model;

import com.octagram.pollet.gifticon.domain.model.GifticonProduct;
import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.survey.domain.model.type.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import jakarta.persistence.*;
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
@Table(name = "survey")
public class Survey {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reward_gifticon_product_id", nullable = true)
	private GifticonProduct gifticonProduct;

	@Column(nullable = false)
	private String title;

	@Column(nullable = true)
	private String subtitle;

	@Column(nullable = true)
	private String description;

	@Column(nullable = true)
	private String coverUrl;

	@Column(nullable = true)
	private String primaryColor;

	@Column(nullable = true)
	private String secondaryColor;

	@Column(nullable = false)
	private Boolean isTemplate;

	@Column(nullable = false)
	private String creatorName;

	@Column(nullable = false)
	private String purpose;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender targetGender;

	@Column(nullable = false)
	private LocalDate startDate;

	@Column(nullable = false)
	private LocalDate endDate;

	// 설문 시작 시간, 종료 시간 추가
	@Column(nullable = false)
	private LocalDateTime startTime;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EndCondition endCondition;

	@Column(nullable = false)
	private LocalDateTime endTime;

	@Column(nullable = false)
	private LocalDateTime responseExpireDate;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PrivacyType privacyType;

	@Column(nullable = false)
	private Long privacyContents;

	@Column(nullable = false)
	private Long privacyPurposeType;

	@Column(nullable = true)
	private String privacyPurposeValue;

	@Column(nullable = false)
	private LocalDateTime privacyExpireDate;

	@Column(nullable = false)
	private Long estimatedTime;

	@Column(nullable = false)
	private Long requireResponseCount;

	@Column(nullable = false)
	private Long currentResponseCount;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private RewardType rewardType;

	@Column(nullable = true)
	private Long rewardPoint;

	@Column(nullable = false)
	private Long availablePoint;
}
