package com.octagram.pollet.survey.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.survey.domain.model.Survey;
import com.octagram.pollet.survey.domain.model.SurveySubmission;
import java.util.Optional;

public interface SurveySubmissionRepository extends JpaRepository<SurveySubmission, Long> {

	boolean existsBySurveyAndMember(Survey survey, Member member);

	int countBySurvey(Survey survey);

	Optional<SurveySubmission> findBySurveyIdAndId(Long surveyId, Long submissionId);
}
