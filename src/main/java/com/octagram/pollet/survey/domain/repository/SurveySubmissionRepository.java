package com.octagram.pollet.survey.domain.repository;

import com.octagram.pollet.survey.domain.model.Survey;
import com.octagram.pollet.survey.domain.model.SurveySubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveySubmissionRepository extends JpaRepository<SurveySubmission, Long> {
	int countBySurvey(Survey survey);
}
