package com.octagram.pollet.survey.application.mapper;

import org.mapstruct.Mapper;

import com.octagram.pollet.survey.domain.model.Tag;
import com.octagram.pollet.survey.presentation.dto.response.TagGetResponse;

@Mapper
public interface TagMapper {

	TagGetResponse toGetResponse(Tag tag);
}
