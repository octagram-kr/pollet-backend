package com.octagram.pollet.member.domain.model;

import com.octagram.pollet.gifticon.domain.model.GifticonCode;
import com.octagram.pollet.member.domain.model.type.TransactionType;
import com.octagram.pollet.survey.domain.model.Survey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "point_history")
public class PointHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_id", nullable = true)
	private Survey survey;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gifticon_code_id", nullable = true)
	private GifticonCode gifticonCode;

	@Column(nullable = true)
	private Long transactionNumber;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private TransactionType transactionType;

	@Column(nullable = true)
	private String transactionDescription;

	@Column(nullable = false)
	private Long amount;

	@Column(nullable = false)
	private Long afterAmount;
}
