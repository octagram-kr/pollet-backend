package com.octagram.pollet.survey.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.octagram.pollet.survey.domain.model.SurveyPointHistory;

public interface SurveyPointHistoryRepository extends JpaRepository<SurveyPointHistory, Long> {
}
