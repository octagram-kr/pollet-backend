package com.octagram.pollet.survey.infrastructure.persistence;

import com.octagram.pollet.survey.domain.model.QSurveyTag;
import com.octagram.pollet.survey.domain.model.QTag;
import com.octagram.pollet.survey.domain.model.Tag;
import com.octagram.pollet.survey.domain.repository.SurveyTagRepositoryCustom;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SurveyTagRepositoryImpl implements SurveyTagRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Tag> findAllUsedTags() {
		QSurveyTag surveyTag = QSurveyTag.surveyTag;
		QTag tag = QTag.tag;

		return queryFactory
			.select(surveyTag.tag)
			.distinct()
			.from(surveyTag)
			.fetch();
	}
}
