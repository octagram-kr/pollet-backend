package com.octagram.pollet.survey.application.mapper;

import com.octagram.pollet.survey.domain.model.Question;
import com.octagram.pollet.survey.domain.model.QuestionOption;
import com.octagram.pollet.survey.presentation.dto.response.QuestionOptionListResponse;
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
}
