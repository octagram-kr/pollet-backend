package com.octagram.pollet.survey.application.mapper;

import com.octagram.pollet.survey.domain.model.Question;
import com.octagram.pollet.survey.domain.model.QuestionOption;
import com.octagram.pollet.survey.presentation.dto.response.QuestionOptionListResponse;
import com.octagram.pollet.survey.presentation.dto.response.QuestionStatisticsResponse;
import com.octagram.pollet.survey.presentation.dto.response.standard.QuestionResponse;
import com.octagram.pollet.survey.presentation.dto.response.TargetQuestionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper
public interface QuestionMapper {

	QuestionOptionListResponse toQuestionOptionListResponse(QuestionOption questionOption);

	default TargetQuestionResponse toTargetQuestionResponse(
			Question question, List<QuestionOptionListResponse> questionOptions) {
		return new TargetQuestionResponse(
				question.getId(),
				question.getTitle(),
				questionOptions
		);
	}

	@Mapping(source = "type", target = "questionType")
	QuestionResponse toResponse(Question question);

	@Mapping(target = "questionId", source = "question.id")
	@Mapping(target = "questionTitle", source = "question.title")
	@Mapping(target = "questionType", expression = "java(question.getType().name())")
	@Mapping(target = "answeredCount", source = "answeredCount")
	@Mapping(target = "options", source = "options")
	@Mapping(target = "etcAnswers", source = "etcAnswers")
	QuestionStatisticsResponse.QuestionResult toQuestionResult(
			Question question,
			List<QuestionStatisticsResponse.OptionResult> options,
			int answeredCount,
			List<String> etcAnswers
	);

	@Mapping(target = "optionId", source = "option.id")
	@Mapping(target = "optionText", source = "option.content")
	@Mapping(target = "responseCount", source = "responseCount")
	@Mapping(target = "responseRatio", source = "responseRatio")
	QuestionStatisticsResponse.OptionResult toOptionResult(
			QuestionOption option,
			int responseCount,
			double responseRatio
	);


}
