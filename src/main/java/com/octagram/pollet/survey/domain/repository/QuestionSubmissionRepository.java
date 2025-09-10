package com.octagram.pollet.survey.domain.repository;

import com.octagram.pollet.survey.domain.model.Question;
import com.octagram.pollet.survey.domain.model.QuestionSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionSubmissionRepository extends JpaRepository<QuestionSubmission, Long> {
	int countByQuestion(Question question);

	List<String> findAnswersByQuestion(Question question);
}
