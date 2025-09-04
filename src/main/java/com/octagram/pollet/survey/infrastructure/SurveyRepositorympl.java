package com.octagram.pollet.survey.infrastructure;

import com.octagram.pollet.survey.domain.model.QSurvey;
import com.octagram.pollet.survey.domain.repository.SurveyRepositoryCustom;
import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class SurveyRepositorympl implements SurveyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public long countActive() {
        QSurvey survey = QSurvey.survey;

        LocalDateTime now = LocalDateTime.now();

        return queryFactory
                .select(survey.count())
                .from(survey)
                .where(
                        survey.startDateTime.loe(now),
                        survey.endDateTime.goe(now)
                )
                .fetchOne();
    }
}
