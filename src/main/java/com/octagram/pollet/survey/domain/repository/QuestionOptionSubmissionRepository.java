package com.octagram.pollet.survey.domain.repository;

import com.octagram.pollet.survey.domain.model.QuestionOption;
import com.octagram.pollet.survey.domain.model.QuestionOptionSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionOptionSubmissionRepository extends JpaRepository<QuestionOptionSubmission, Long> {

	int countByQuestionOption(QuestionOption questionOption);

	List<QuestionOptionSubmission> findByQuestionSubmissionId(Long questionSubmissionId);
}
