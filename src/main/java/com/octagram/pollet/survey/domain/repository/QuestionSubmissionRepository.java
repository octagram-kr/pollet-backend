package com.octagram.pollet.survey.domain.repository;

import com.octagram.pollet.survey.domain.model.Question;
import com.octagram.pollet.survey.domain.model.QuestionSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface QuestionSubmissionRepository extends JpaRepository<QuestionSubmission, Long> {
	int countByQuestion(Question question);

	@Query("select qs.answer from QuestionSubmission qs where qs.question = :question and qs.answer is not null")
	List<String> findAnswersByQuestion(@Param("question") Question question);
}
