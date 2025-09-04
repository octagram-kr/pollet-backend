package com.octagram.pollet.survey.repository;

import com.octagram.pollet.survey.domain.model.QSurvey;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

@RequiredArgsConstructor
public class SurveyRepositoryCustomImpl implements SurveyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public long countActive(LocalDate today) {
        QSurvey survey = QSurvey.survey;

        return queryFactory
                .select(survey.count())
                .from(survey)
                .where(
                        survey.startDate.loe(today),
                        survey.endDate.goe(today)
                )
                .fetchOne();
    }
}

