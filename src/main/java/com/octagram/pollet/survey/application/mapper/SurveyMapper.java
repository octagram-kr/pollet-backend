package com.octagram.pollet.survey.application.mapper;

import com.octagram.pollet.survey.presentation.dto.response.SurveyGetRecentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.octagram.pollet.gifticon.application.mapper.GifticonMapper;
import com.octagram.pollet.survey.domain.model.Survey;
import com.octagram.pollet.survey.presentation.dto.response.SurveyGetDetailResponse;
import com.octagram.pollet.survey.presentation.dto.response.SurveyGetResponse;

@Mapper(uses = {GifticonMapper.class})
public interface SurveyMapper {

	SurveyGetResponse toGetResponse(Survey survey);

	@Mapping(source = "rewardPoint", target = "rewardPoint", defaultValue = "0L")
	SurveyGetDetailResponse toGetDetailResponse(Survey survey);

	SurveyGetRecentResponse toGetRecentResponse(Survey survey);
}
