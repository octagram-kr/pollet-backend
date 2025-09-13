package com.octagram.pollet.survey.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.octagram.pollet.global.domain.model.BaseEntity;
import com.octagram.pollet.survey.domain.model.type.QuestionType;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "question")
public class Question extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_id", nullable = false)
	private Survey survey;

	@Builder.Default
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<QuestionOption> options = new ArrayList<>();

	@Column(nullable = false)
	private Long order;

	@Column(nullable = false)
	private Long page;

	@Column(nullable = false)
	private String title;

	@Column(nullable = true)
	private String description;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private QuestionType type;

	@Column(nullable = false)
	private Boolean isRequired;

	@Column(nullable = false)
	private Boolean isCheckTarget;

	@Column(nullable = false)
	private Boolean isCheckDiligent;

	@Column(nullable = true)
	private String imageUrl;

	public void addOption(QuestionOption option) {
		this.options.add(option);
		option.setQuestion(this);
	}
}
