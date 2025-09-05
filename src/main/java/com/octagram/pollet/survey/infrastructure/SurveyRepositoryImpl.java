package com.octagram.pollet.survey.infrastructure;

import static com.octagram.pollet.gifticon.domain.model.QGifticonProduct.*;
import static com.octagram.pollet.survey.domain.model.QSurvey.*;

import com.octagram.pollet.survey.domain.model.QSurvey;
import com.octagram.pollet.survey.domain.model.Survey;
import com.octagram.pollet.survey.domain.repository.SurveyRepositoryCustom;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SurveyRepositoryImpl implements SurveyRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public long getActiveCount(LocalDateTime now) {
		QSurvey survey = QSurvey.survey;

		return queryFactory
			.select(survey.count())
			.from(survey)
			.where(
				survey.startDateTime.loe(now),
				survey.endDateTime.goe(now)
			)
			.fetchOne();
	}

	@Override
	public Optional<Survey> findByIdQueryDsl(Long id) {
		return Optional.ofNullable(
			queryFactory
				.selectFrom(survey)
				.join(survey.gifticonProduct, gifticonProduct)
				.fetchJoin()
				.fetchOne()
		);
	}
}
