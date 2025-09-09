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
import com.octagram.pollet.survey.presentation.dto.response.*;
import com.octagram.pollet.survey.domain.repository.SurveyRepository;
import com.octagram.pollet.survey.domain.repository.SurveyTagRepository;
import com.octagram.pollet.survey.domain.repository.TagRepository;
import com.octagram.pollet.survey.presentation.dto.response.standard.QuestionResponse;
import com.octagram.pollet.survey.presentation.dto.response.standard.SurveyResponse;
import com.octagram.pollet.survey.presentation.dto.response.standard.TagResponse;

import org.springframework.transaction.annotation.Transactional;
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
	public List<TagResponse> getAllTags() {
		return tagRepository.findAll().stream()
			.map(tagMapper::toResponse)
			.toList();
	}

	@Transactional(readOnly = true)
	public List<TagResponse> getAllUsedTags() {
		return surveyTagRepository.findAllUsedTags().stream()
			.map(tagMapper::toResponse)
			.toList();
	}

	@Transactional(readOnly = true)
	public SurveyResponse getSurveyById(Long surveyId) {
		Survey survey = surveyRepository.findByIdQueryDsl(surveyId)
			.orElseThrow(() -> new BusinessException(SurveyErrorCode.SURVEY_NOT_FOUND));
		return surveyMapper.toSurveyResponse(survey);
	}

	@Transactional(readOnly = true)
	public List<TagResponse> getTagsBySurveyId(Long surveyId) {
		return surveyTagRepository.findBySurveyId(surveyId).stream()
			.map(surveyTag -> tagMapper.toResponse(surveyTag.getTag()))
			.toList();
	}

	@Transactional(readOnly = true)
	public long getActiveCount() {
		LocalDateTime now = LocalDateTime.now();
		return surveyRepository.getActiveCount(now);
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
	public List<SurveyResponse> filterSurveys(SurveyFilterRequest filter, Pageable pageable) {
		List<String> allTags = new ArrayList<>();
		if (filter.genders() != null) allTags.addAll(filter.genders());
		if (filter.ages() != null) allTags.addAll(filter.ages());
		if (filter.jobs() != null) allTags.addAll(filter.jobs());
		if (filter.tags() != null) allTags.addAll(filter.tags());
		List<Survey> surveysResult = surveyRepository.findByFilter(allTags, filter, pageable);
		return surveysResult.stream()
			.map(surveyMapper::toSurveyResponse)
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
