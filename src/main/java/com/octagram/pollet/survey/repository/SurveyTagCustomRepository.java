package com.octagram.pollet.survey.repository;

import com.octagram.pollet.survey.domain.model.Tag;
import java.util.List;

public interface SurveyTagCustomRepository {
    List<Tag> findAllUsedTags();
}
