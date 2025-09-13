package com.octagram.pollet.survey.application.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.octagram.pollet.survey.domain.model.QuestionOptionSubmission;
import com.octagram.pollet.survey.domain.model.QuestionSubmission;
import com.octagram.pollet.survey.presentation.dto.request.QuestionOptionSubmissionRequest;

@Mapper
public interface QuestionOptionSubmissionMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "questionSubmission", expression = "java(questionSubmission)")
	@Mapping(target = "questionOption.id", source = "questionOptionId")
	QuestionOptionSubmission toEntityFromQuestionOptionSubmissionRequest(
		QuestionOptionSubmissionRequest questionOptionSubmissionRequest,
		@Context QuestionSubmission questionSubmission
	);
}
