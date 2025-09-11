package com.octagram.pollet.survey.domain.repository;

import com.octagram.pollet.survey.domain.model.Question;
import com.octagram.pollet.survey.domain.model.QuestionSubmission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionSubmissionRepository extends JpaRepository<QuestionSubmission, Long>, QuestionSubmissionRepositoryCustom {

	int countByQuestion(Question question);

	Slice<QuestionSubmission> findBySurveySubmissionId(Long submissionId, Pageable pageable);
}
