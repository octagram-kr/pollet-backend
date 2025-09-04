package com.octagram.pollet.survey.repository;

import com.octagram.pollet.survey.domain.model.QSurvey;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class SurveyRepositoryCustomImpl implements SurveyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public long countActive() { // now() 활용으로 today 제거
        QSurvey survey = QSurvey.survey;

        LocalDateTime now = LocalDateTime.now(); // 현재 시간

        return queryFactory
                .select(survey.count()) // 활성 설문 개수 카운트
                .from(survey)
                .where(
                        survey.startDateTime.loe(now), // 시작 시간이 현재 시간 이전/동일
                        survey.endDateTime.goe(now)    // 종료 시간이 현재 시간 이후/동일
                )
                .fetchOne();
    }
}

