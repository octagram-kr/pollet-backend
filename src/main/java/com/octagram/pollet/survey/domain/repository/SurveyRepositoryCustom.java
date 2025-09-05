package com.octagram.pollet.survey.domain.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import com.octagram.pollet.survey.domain.model.Survey;

public interface SurveyRepositoryCustom {

	long getActiveCount(LocalDateTime now);
	Optional<Survey> findByIdQueryDsl(Long id);
}
