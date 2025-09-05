package com.octagram.pollet.survey.domain.repository;

import java.time.LocalDateTime;

public interface SurveyRepositoryCustom {

    long countActive(LocalDateTime now);
}
