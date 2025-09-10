package com.octagram.pollet.survey.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.octagram.pollet.survey.domain.model.SurveyTag;
import com.octagram.pollet.survey.domain.model.Tag;
import com.octagram.pollet.survey.presentation.dto.response.standard.TagResponse;

@Mapper
public interface TagMapper {

	TagResponse toResponse(Tag tag);

	@Mapping(source = "tag", target = ".")
	TagResponse toResponse(SurveyTag surveyTag);
}
