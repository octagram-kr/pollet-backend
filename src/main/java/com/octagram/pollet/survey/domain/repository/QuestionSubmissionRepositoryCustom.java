package com.octagram.pollet.survey.domain.repository;

import com.octagram.pollet.survey.domain.model.Question;
import java.util.List;

public interface QuestionSubmissionRepositoryCustom {

	List<String> findAnswersByQuestion(Question question);
}
