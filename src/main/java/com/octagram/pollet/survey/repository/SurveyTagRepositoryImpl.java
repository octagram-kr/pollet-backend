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
        QSurveyTag surveyTag = QSurveyTag.surveyTag;
        QTag tag = QTag.tag;

        return queryFactory
                .select(surveyTag.tag)
                .distinct()
                .from(surveyTag)
                .fetch();
    }
}
