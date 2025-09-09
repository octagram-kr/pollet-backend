package com.octagram.pollet.survey.infrastructure;

import static com.octagram.pollet.survey.domain.model.QQuestion.*;
import static com.octagram.pollet.survey.domain.model.QQuestionOption.*;

import com.octagram.pollet.survey.domain.model.QQuestion;
import com.octagram.pollet.survey.domain.model.QQuestionOption;
import com.octagram.pollet.survey.domain.model.Question;
import com.octagram.pollet.survey.domain.model.QuestionOption;
import com.octagram.pollet.survey.domain.repository.QuestionRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<QuestionOption> findOptionsByQuestionId(Long questionId) {
		QQuestionOption questionOption = QQuestionOption.questionOption;

		return queryFactory.selectFrom(questionOption)
				.where(questionOption.question.id.eq(questionId))
				.fetch();
	}

	@Override
	public List<Question> findQuestionsBySurveyId(Long surveyId) {
		QQuestion question = QQuestion.question;

		return queryFactory.selectFrom(question)
				.where(question.survey.id.eq(surveyId))
				.fetch();
	}

	@Override
	public List<Question> findBySurveyId(Long surveyId) {
		return queryFactory
			.selectFrom(question)
			.leftJoin(question.options, questionOption).fetchJoin()
			.where(question.survey.id.eq(surveyId))
			.orderBy(question.order.asc())
			.orderBy(questionOption.order.asc())
			.fetch();
	}
}
