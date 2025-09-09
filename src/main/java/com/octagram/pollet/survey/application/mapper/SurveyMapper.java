package com.octagram.pollet.survey.application.mapper;

import com.octagram.pollet.survey.domain.model.type.RewardType;
import com.octagram.pollet.survey.presentation.dto.response.SurveyGetRecentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.octagram.pollet.gifticon.application.mapper.GifticonMapper;
import com.octagram.pollet.survey.domain.model.Survey;
import com.octagram.pollet.survey.presentation.dto.response.SurveyFilterResponse;
import com.octagram.pollet.survey.presentation.dto.response.SurveyGetDetailResponse;
import com.octagram.pollet.survey.presentation.dto.response.SurveyGetResponse;
import org.mapstruct.Named;

@Mapper(uses = {GifticonMapper.class, TagMapper.class})
public interface SurveyMapper {

	SurveyGetResponse toGetResponse(Survey survey);

	@Mapping(source = "rewardPoint", target = "rewardPoint", defaultValue = "0L")
	SurveyGetDetailResponse toGetDetailResponse(Survey survey);

	@Mapping(source = "survey", target = "gifticonName", qualifiedByName = "resolveGifticonName")
	@Mapping(source = "rewardPoint", target = "rewardPoint")
	SurveyGetRecentResponse toGetRecentResponse(Survey survey);

	@Named("resolveGifticonName")
	default String resolveGifticonName(Survey survey) {
		if (survey.getRewardType() == RewardType.GIFTICON && survey.getGifticonProduct() != null) {
			return survey.getGifticonProduct().getName();
		}
		return null; // 보상 상품이 없거나 GIFTICON이 아닌 경우 null 반환
	}

	@Mapping(source = "surveyTags", target = "tags")
	@Mapping(source = "gifticonProduct", target = "rewardGifticonProduct")
	SurveyFilterResponse toFilterResponse(Survey survey);
}
