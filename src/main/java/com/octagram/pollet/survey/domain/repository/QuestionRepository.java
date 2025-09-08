package com.octagram.pollet.survey.domain.repository;

import com.octagram.pollet.survey.domain.model.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionOption, Long>, QuestionRepositoryCustom {
}
