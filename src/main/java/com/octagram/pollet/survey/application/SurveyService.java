package com.octagram.pollet.survey.application;

import com.octagram.pollet.global.exception.BusinessException;
import com.octagram.pollet.survey.application.mapper.QuestionMapper;
import com.octagram.pollet.survey.application.mapper.SurveyMapper;
import com.octagram.pollet.survey.application.mapper.TagMapper;
import com.octagram.pollet.survey.domain.model.Question;
import com.octagram.pollet.survey.domain.model.Survey;
import com.octagram.pollet.survey.domain.repository.QuestionRepository;
import com.octagram.pollet.survey.domain.status.SurveyErrorCode;
import com.octagram.pollet.survey.presentation.dto.request.SurveyFilterRequest;
import com.octagram.pollet.survey.presentation.dto.response.SurveyFilterResponse;
import com.octagram.pollet.survey.presentation.dto.response.SurveyGetDetailResponse;
import com.octagram.pollet.survey.presentation.dto.response.SurveyGetResponse;
import com.octagram.pollet.survey.presentation.dto.response.TagGetResponse;
import com.octagram.pollet.survey.presentation.dto.response.*;
import com.octagram.pollet.survey.domain.repository.SurveyRepository;
import com.octagram.pollet.survey.domain.repository.SurveyTagRepository;
import com.octagram.pollet.survey.domain.repository.TagRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {

	private final SurveyRepository surveyRepository;
	private final TagRepository tagRepository;
	private final QuestionRepository questionRepository;
	private final SurveyTagRepository surveyTagRepository;
	private final SurveyMapper surveyMapper;
	private final TagMapper tagMapper;
	private final QuestionMapper questionMapper;

	@Transactional(readOnly = true)
	public List<TagGetResponse> getAllTags() {
		return tagRepository.findAll().stream()
			.map(tagMapper::toGetResponse)
			.toList();
	}

	@Transactional(readOnly = true)
	public List<TagGetResponse> getAllUsedTags() {
		return surveyTagRepository.findAllUsedTags().stream()
			.map(tagMapper::toGetResponse)
			.toList();
	}

	@Transactional(readOnly = true)
	public SurveyGetDetailResponse getSurveyById(Long surveyId) {
		Survey survey = surveyRepository.findByIdQueryDsl(surveyId)
			.orElseThrow(() -> new BusinessException(SurveyErrorCode.SURVEY_NOT_FOUND));
		return surveyMapper.toGetDetailResponse(survey);
	}

	@Transactional(readOnly = true)
	public List<TagGetResponse> getTagsBySurveyId(Long surveyId) {
		return surveyTagRepository.findBySurveyId(surveyId).stream()
			.map(surveyTag -> tagMapper.toGetResponse(surveyTag.getTag()))
			.toList();
	}

	@Transactional(readOnly = true)
	public long getActiveCount() {
		LocalDateTime now = LocalDateTime.now();
		return surveyRepository.getActiveCount(now);
	}

	@Transactional(readOnly = true)
	public Page<SurveyGetResponse> searchSurveys(String keyword, Pageable pageable) {
		return surveyRepository.findByTitleContainingIgnoreCase(keyword, pageable)
			.map(surveyMapper::toGetResponse);
	}

	@Transactional(readOnly = true)
	public List<SurveyGetRecentResponse> getLatest4Surveys() {
		return surveyRepository.findTop4ByOrderByCreatedAt().stream()
				.map(surveyMapper::toGetRecentResponse)
				.toList();
	}

	@Transactional(readOnly = true)
	public List<TargetQuestionResponse> getLatest4SurveysTargetQuestions() {
		List<Survey> latestSurveys = surveyRepository.findTop4ByOrderByCreatedAt();

		return latestSurveys.stream().map(survey -> {
			List<Question> questions = questionRepository.findQuestionsBySurveyId(survey.getId());

			Question targetQuestion = questions.stream()
					.filter(Question::getIsCheckTarget)
					.findFirst()
					.orElseThrow(() -> new BusinessException(SurveyErrorCode.QUESTION_NOT_FOUND));

			List<QuestionOptionListResponse> questionOptions = questionRepository.findOptionsByQuestionId(targetQuestion.getId())
					.stream()
					.map(questionMapper::toQuestionOptionListResponse)
					.toList();

			return questionMapper.toTargetQuestionResponse(targetQuestion, questionOptions);
		}).toList();
	}

	@Transactional(readOnly = true)
	public List<SurveyFilterResponse> filterSurveys(SurveyFilterRequest filter, Pageable pageable) {
		List<String> allTags = new ArrayList<>();
		if (filter.genders() != null) allTags.addAll(filter.genders());
		if (filter.ages() != null) allTags.addAll(filter.ages());
		if (filter.jobs() != null) allTags.addAll(filter.jobs());
		if (filter.tags() != null) allTags.addAll(filter.tags());
		List<Survey> surveysResult = surveyRepository.findByFilter(allTags, filter, pageable);
		return surveysResult.stream()
			.map(surveyMapper::toFilterResponse)
			.toList();
	}

	@Transactional(readOnly = true)
	public List<QuestionResponse> getQuestions(Long surveyId) {
		List<Question> result = questionRepository.findBySurveyId(surveyId);
		return result.stream()
			.map(questionMapper::toResponse)
			.toList();
	}
}
