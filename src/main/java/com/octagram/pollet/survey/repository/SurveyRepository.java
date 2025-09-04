package com.octagram.pollet.survey.repository;

import com.octagram.pollet.survey.domain.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long>, SurveyRepositoryCustom {
}
