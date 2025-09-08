package com.octagram.pollet.survey.application.mapper;

import com.octagram.pollet.survey.domain.model.Question;
import com.octagram.pollet.survey.domain.model.QuestionOption;
import com.octagram.pollet.survey.presentation.dto.response.QuestionOptionListResponse;
import com.octagram.pollet.survey.presentation.dto.response.TargetQuestionResponse;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper
public interface QuestionMapper {

	QuestionOptionListResponse toQuestionOptionListResponse(QuestionOption questionOption);

	default TargetQuestionResponse totargetQuestionResponse(
			Question question, List<QuestionOptionListResponse> questionOptions) {
		return new TargetQuestionResponse(
				question.getId(),
				question.getTitle(),
				questionOptions
		);
	}
}
