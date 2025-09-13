package com.octagram.pollet.survey.application.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.survey.domain.model.Survey;
import com.octagram.pollet.survey.domain.model.SurveySubmission;
import com.octagram.pollet.survey.presentation.dto.request.SurveySubmissionRequest;

@Mapper(uses = {QuestionSubmissionMapper.class})
public interface SurveySubmissionMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "survey", expression = "java(survey)")
	@Mapping(target = "member", expression = "java(member)")
	SurveySubmission toEntityFromSurveySubmissionRequest(
		SurveySubmissionRequest request,
		@Context Survey survey,
		@Context Member member
	);
}
