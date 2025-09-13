package com.octagram.pollet.survey.infrastructure.persistence;

import com.octagram.pollet.survey.domain.model.QQuestionSubmission;
import com.octagram.pollet.survey.domain.model.Question;
import com.octagram.pollet.survey.domain.repository.QuestionSubmissionRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionSubmissionRepositoryImpl implements QuestionSubmissionRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<String> findAnswersByQuestion(Question question) {
		QQuestionSubmission questionSubmission = QQuestionSubmission.questionSubmission;

		return queryFactory.select(questionSubmission.answer)
				.from(questionSubmission)
				.where(
						questionSubmission.question.eq(question)
								.and(questionSubmission.answer.isNotNull())
				)
				.fetch();
	}
}
