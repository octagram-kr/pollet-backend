package com.octagram.pollet.survey.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.octagram.pollet.survey.domain.model.QuestionOptionSubmission;

public interface QuestionOptionSubmissionRepository extends JpaRepository<QuestionOptionSubmission, Long> {
}
