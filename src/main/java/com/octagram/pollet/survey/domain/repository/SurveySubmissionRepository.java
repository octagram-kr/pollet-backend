package com.octagram.pollet.survey.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.octagram.pollet.survey.domain.model.SurveySubmission;

public interface SurveySubmissionRepository extends JpaRepository<SurveySubmission, Long> {
}
