package com.octagram.pollet.survey.domain.repository;

import com.octagram.pollet.survey.domain.model.Question;
import com.octagram.pollet.survey.domain.model.QuestionOption;
import java.util.List;

public interface QuestionRepositoryCustom {

	// QuestionID를 기반으로 QuestionOption 리스트 조회
	List<QuestionOption> findOptionsByQuestionId(Long questionId);

	// SurveyID를 기반으로 Question 리스트 조회
	List<Question> findQuestionsBySurveyId(Long surveyId);

	List<Question> findBySurveyId(Long surveyId);
}
