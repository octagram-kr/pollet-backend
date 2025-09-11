package com.octagram.pollet.survey.application;

import static com.octagram.pollet.survey.domain.model.QSurveySubmission.*;

import com.octagram.pollet.global.exception.BusinessException;
import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.survey.application.mapper.QuestionMapper;
import com.octagram.pollet.survey.application.mapper.QuestionOptionSubmissionMapper;
import com.octagram.pollet.survey.application.mapper.QuestionSubmissionMapper;
import com.octagram.pollet.survey.application.mapper.SurveyMapper;
import com.octagram.pollet.survey.application.mapper.SurveySubmissionMapper;
import com.octagram.pollet.survey.application.mapper.TagMapper;
import com.octagram.pollet.survey.domain.model.Question;
import com.octagram.pollet.survey.domain.model.QuestionOptionSubmission;
import com.octagram.pollet.survey.domain.model.QuestionSubmission;
import com.octagram.pollet.survey.domain.model.Survey;
import com.octagram.pollet.survey.domain.model.SurveySubmission;
import com.octagram.pollet.survey.domain.repository.QuestionOptionSubmissionRepository;
import com.octagram.pollet.survey.domain.repository.QuestionRepository;
import com.octagram.pollet.survey.domain.repository.QuestionSubmissionRepository;
import com.octagram.pollet.survey.domain.repository.SurveySubmissionRepository;
import com.octagram.pollet.survey.domain.status.SurveyErrorCode;
import com.octagram.pollet.survey.presentation.dto.request.QuestionOptionSubmissionRequest;
import com.octagram.pollet.survey.presentation.dto.request.QuestionSubmissionRequest;
import com.octagram.pollet.survey.presentation.dto.request.SurveyFilterRequest;
import com.octagram.pollet.survey.presentation.dto.request.SurveySubmissionRequest;
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
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SurveyService {

	private final SurveyRepository surveyRepository;
	private final TagRepository tagRepository;
	private final QuestionRepository questionRepository;
	private final SurveyTagRepository surveyTagRepository;
	private final SurveySubmissionRepository surveySubmissionRepository;
	private final QuestionSubmissionRepository questionSubmissionRepository;
	private final QuestionOptionSubmissionRepository questionOptionSubmissionRepository;
	private final SurveyMapper surveyMapper;
	private final TagMapper tagMapper;
	private final QuestionMapper questionMapper;
	private final SurveySubmissionMapper surveySubmissionMapper;
	private final QuestionSubmissionMapper questionSubmissionMapper;
	private final QuestionOptionSubmissionMapper questionOptionSubmissionMapper;

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

	@Transactional
	public void submitSurvey(Long surveyId, Member member, SurveySubmissionRequest request) {
		Survey survey = surveyRepository.findById(surveyId)
			.orElseThrow(() -> new BusinessException(SurveyErrorCode.SURVEY_NOT_FOUND));

		SurveySubmission surveySubmission = saveSurveySubmission(request, survey, member);
		List<QuestionSubmission> questionSubmissions = saveQuestionSubmissions(request.questionSubmissions(), surveySubmission);
		saveQuestionOptionSubmissions(request.questionSubmissions(), questionSubmissions);
	}

	private SurveySubmission saveSurveySubmission(SurveySubmissionRequest request, Survey survey, Member member) {
		SurveySubmission surveySubmission = surveySubmissionMapper.toEntityFromSurveySubmissionRequest(request, survey, member);
		return surveySubmissionRepository.save(surveySubmission);
	}

	private List<QuestionSubmission> saveQuestionSubmissions(List<QuestionSubmissionRequest> requests, SurveySubmission surveySubmission) {
		List<QuestionSubmission> questions = requests.stream()
			.map(req -> questionSubmissionMapper.toEntityFromQuestionSubmissionRequest(req, surveySubmission))
			.toList();
		questionSubmissionRepository.saveAll(questions);
		return questions;
	}

	private void saveQuestionOptionSubmissions(List<QuestionSubmissionRequest> requests, List<QuestionSubmission> questionSubmissions) {
		List<QuestionOptionSubmission> optionSubs = new ArrayList<>();
		for (int i = 0; i < questionSubmissions.size(); i++) {
			QuestionSubmission submission = questionSubmissions.get(i);
			QuestionSubmissionRequest submissionRequest = requests.get(i);

			submissionRequest.questionOptionSubmissions().stream()
				.map(request -> questionOptionSubmissionMapper.toEntityFromQuestionOptionSubmissionRequest(request, submission))
				.forEach(optionSubs::add);
		}

		if (!optionSubs.isEmpty()) {
			questionOptionSubmissionRepository.saveAll(optionSubs);
		}
	}
}
