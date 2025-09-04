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
    public long countActive(LocalDate today) {
        QSurvey survey = QSurvey.survey;

        LocalDateTime startOfDay = today.atStartOfDay(); // LocalDate -> LocalDateTime 시작 시간
        LocalDateTime endOfDay = today.atTime(23, 59, 59); // LocalDate -> LocalDateTime 종료 시간

        return queryFactory
                .select(survey.count())
                .from(survey)
                .where(
                        survey.startDateTime.loe(endOfDay), // 현재 '끝 시간' 포함 조건 확인
                        survey.endDateTime.goe(startOfDay)  // 현재 '시작 시간' 포함 조건 확인
                )
                .fetchOne();
    }
}

