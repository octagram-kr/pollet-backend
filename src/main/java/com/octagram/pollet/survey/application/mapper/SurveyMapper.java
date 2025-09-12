package com.octagram.pollet.survey.application.mapper;

import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.octagram.pollet.gifticon.application.mapper.GifticonMapper;
import com.octagram.pollet.survey.domain.model.Survey;
import com.octagram.pollet.survey.domain.model.type.RewardType;
import com.octagram.pollet.survey.presentation.dto.response.SurveyGetRecentResponse;
import com.octagram.pollet.survey.presentation.dto.response.SurveyMetadataResponse;
import com.octagram.pollet.survey.presentation.dto.response.standard.SurveyResponse;
import com.octagram.pollet.survey.presentation.dto.response.standard.SurveyWithQuestionResponse;

@Mapper(uses = {GifticonMapper.class, TagMapper.class})
public interface SurveyMapper {

	@Mapping(source = "rewardPoint", target = "rewardPoint", defaultValue = "0L")
	SurveyResponse toSurveyResponse(Survey survey);

	@Mapping(source = "rewardPoint", target = "rewardPoint", defaultValue = "0L")
	SurveyWithQuestionResponse toSurveyWithQuestionResponse(Survey survey);

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

	@Mapping(target = "status", expression = "java(resolveSurveyStatus(survey.getStartDateTime(), survey.getEndDateTime()))")
	SurveyMetadataResponse toSurveyMetadataResponse(Survey survey);

	default String resolveSurveyStatus(LocalDateTime start, LocalDateTime end) {
		LocalDateTime now = LocalDateTime.now();
		if (now.isBefore(start)) return "대기중";
		if (now.isAfter(end)) return "종료";
		return "진행중";
	}
}
