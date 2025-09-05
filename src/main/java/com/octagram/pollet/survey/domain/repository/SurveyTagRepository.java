package com.octagram.pollet.survey.domain.repository;

import com.octagram.pollet.survey.domain.model.SurveyTag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyTagRepository extends JpaRepository<SurveyTag, Long>, SurveyTagRepositoryCustom {

	List<SurveyTag> findBySurveyId(Long surveyId);
}
