package com.octagram.pollet.survey.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.octagram.pollet.survey.domain.model.SurveyTag;
import com.octagram.pollet.survey.domain.model.Tag;
import com.octagram.pollet.survey.presentation.dto.response.TagGetResponse;
import com.octagram.pollet.survey.presentation.dto.response.TagInSurveyFilterResponse;

@Mapper
public interface TagMapper {

	TagGetResponse toGetResponse(Tag tag);

	@Mapping(source = "tag.name", target = "name")
	TagInSurveyFilterResponse toSurveyFilterResponse(SurveyTag surveyTag);

	List<TagInSurveyFilterResponse> toSurveyFilterResponses(List<SurveyTag> surveyTags);
}
