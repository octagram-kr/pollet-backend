package com.octagram.pollet.survey.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.octagram.pollet.survey.domain.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionRepositoryCustom {

	Slice<Question> findBySurveyId(Long surveyId, Pageable pageable);
}
