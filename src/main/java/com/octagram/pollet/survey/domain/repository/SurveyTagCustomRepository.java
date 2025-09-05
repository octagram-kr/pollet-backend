package com.octagram.pollet.survey.domain.repository;

import com.octagram.pollet.survey.domain.model.Tag;
import java.util.List;

public interface SurveyTagCustomRepository {

    List<Tag> findAllUsedTags();
}
