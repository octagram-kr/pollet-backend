package com.octagram.pollet.survey.domain.repository;

import com.octagram.pollet.survey.domain.model.Survey;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long>, SurveyRepositoryCustom {

	Page<Survey> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
