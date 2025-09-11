package com.octagram.pollet.survey.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.octagram.pollet.survey.domain.model.QuestionSubmission;

public interface QuestionSubmissionRepository extends JpaRepository<QuestionSubmission, Long> {
}
