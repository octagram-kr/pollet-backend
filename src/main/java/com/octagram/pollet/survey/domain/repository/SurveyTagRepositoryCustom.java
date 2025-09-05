package com.octagram.pollet.survey.domain.repository;

import com.octagram.pollet.survey.domain.model.Tag;

import java.util.List;

public interface SurveyTagRepositoryCustom {

	List<Tag> findAllUsedTags();
}
