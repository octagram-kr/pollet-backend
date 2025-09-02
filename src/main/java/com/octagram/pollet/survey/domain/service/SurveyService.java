package com.octagram.pollet.survey.domain.service;

import com.octagram.pollet.global.exception.BusinessException;
import com.octagram.pollet.survey.domain.model.Survey;
import com.octagram.pollet.survey.domain.status.SurveyErrorCode;
import com.octagram.pollet.survey.presentation.dto.response.SurveyDetailResponse;
import com.octagram.pollet.survey.presentation.dto.response.TagResponse;
import com.octagram.pollet.survey.repository.SurveyRepository;
import com.octagram.pollet.survey.repository.SurveyTagRepository;
import com.octagram.pollet.survey.repository.TagRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final TagRepository tagRepository;
    private final SurveyRepository surveyRepository;
    private final SurveyTagRepository surveyTagRepository;

    @Transactional(readOnly = true)
    public List<TagResponse> getAllTags() {
        return tagRepository.findAll().stream()
                .map(TagResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TagResponse> getAllUsedTags() {
        return surveyTagRepository.findAllUsedTags().stream()
                .map(TagResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public SurveyDetailResponse getSurveyById(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new BusinessException(SurveyErrorCode.SURVEY_NOT_FOUND));

        List<TagResponse> tags = surveyTagRepository.findBySurveyId(surveyId).stream()
                .map(surveyTag -> TagResponse.from(surveyTag.getTag()))
                .toList();

        return SurveyDetailResponse.from(survey, tags);
    }
}
