package com.octagram.pollet.survey.domain.repository;

import com.octagram.pollet.survey.domain.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long>, SurveyRepositoryCustom {

	List<Survey> findTop4ByOrderByCreatedAt();
}
