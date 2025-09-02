package com.octagram.pollet.survey.repository;

import com.octagram.pollet.survey.domain.model.QSurveyTag;
import com.octagram.pollet.survey.domain.model.QTag;
import com.octagram.pollet.survey.domain.model.Tag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SurveyTagRepositoryImpl implements SurveyTagCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Tag> findAllUsedTags() {
        // Q 클래스 정의: SurveyTag, Tag 엔티티
        QSurveyTag surveyTag = QSurveyTag.surveyTag;
        QTag tag = QTag.tag;

        // QueryDSL 쿼리
        return queryFactory
                .select(surveyTag.tag)
                .distinct()
                .from(surveyTag)
                .fetch(); // 결과 실행 및 반환
    }
}

