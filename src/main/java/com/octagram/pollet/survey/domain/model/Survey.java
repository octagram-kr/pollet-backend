package com.octagram.pollet.survey.domain.model;

import com.octagram.pollet.gifticon.domain.model.GifticonProduct;
import com.octagram.pollet.global.domain.model.BaseEntity;
import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.survey.domain.model.type.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "survey")
public class Survey extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reward_gifticon_product_id", nullable = true)
	private GifticonProduct gifticonProduct;

	@OneToMany(mappedBy = "survey")
	private List<SurveyTag> surveyTags = new ArrayList<>();

	@OneToMany(mappedBy = "survey")
	private List<Question> questions = new ArrayList<>();

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
	private TargetGender targetGender;

	@Column(nullable = false)
	private LocalDateTime startDateTime;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EndCondition endCondition;

	@Column(nullable = false)
	private LocalDateTime endDateTime;

	@Column(nullable = false)
	private LocalDate responseExpireDate;

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
	private LocalDate privacyExpireDate;

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
