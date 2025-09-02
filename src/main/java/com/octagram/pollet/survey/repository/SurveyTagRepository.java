package com.octagram.pollet.survey.repository;

import com.octagram.pollet.survey.domain.model.SurveyTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyTagRepository extends JpaRepository<SurveyTag, Long>, SurveyTagCustomRepository {
}
