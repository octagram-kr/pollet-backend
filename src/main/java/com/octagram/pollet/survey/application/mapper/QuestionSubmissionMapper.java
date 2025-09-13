package com.octagram.pollet.survey.application.mapper;

import org.mapstruct.Context;
import com.octagram.pollet.survey.domain.model.QuestionOptionSubmission;
import com.octagram.pollet.survey.domain.model.QuestionSubmission;
import com.octagram.pollet.survey.presentation.dto.response.ParticipantResultResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
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

	@Mapping(target = "questionId", source = "questionSubmission.question.id")
	@Mapping(target = "questionTitle", source = "questionSubmission.question.title")
	@Mapping(target = "questionType", expression = "java(questionSubmission.getQuestion().getType().name())")
	@Mapping(target = "selectedOptions", source = "optionSubmissions")
	@Mapping(target = "subjectiveAnswer", source = "questionSubmission.answer")
	ParticipantResultResponse.QuestionAnswer toQuestionAnswer(
			QuestionSubmission questionSubmission, List<ParticipantResultResponse.SelectedOption> optionSubmissions);

	@Mapping(target = "optionId", source = "questionOption.id")
	@Mapping(target = "optionText", source = "questionOption.content")
	ParticipantResultResponse.SelectedOption toSelectedOption(QuestionOptionSubmission optionSubmission);
}
