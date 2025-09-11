package com.octagram.pollet.survey.application.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.octagram.pollet.survey.domain.model.QuestionSubmission;
import com.octagram.pollet.survey.domain.model.SurveySubmission;
import com.octagram.pollet.survey.presentation.dto.request.QuestionSubmissionRequest;

@Mapper(uses = {QuestionOptionSubmissionMapper.class})
public interface QuestionSubmissionMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "surveySubmission", expression = "java(surveySubmission)")
	@Mapping(target = "question.id", source = "questionId")
	QuestionSubmission toEntityFromQuestionSubmissionRequest(
		QuestionSubmissionRequest questionSubmissionRequest,
		@Context SurveySubmission surveySubmission
	);
}
