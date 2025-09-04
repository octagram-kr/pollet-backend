package com.octagram.pollet.survey.domain.repository;

import com.octagram.pollet.survey.domain.model.SurveyTag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Collection;

public interface SurveyTagRepository extends JpaRepository<SurveyTag, Long>, SurveyTagCustomRepository {

    Collection<SurveyTag> findBySurveyId(Long surveyId);
}
