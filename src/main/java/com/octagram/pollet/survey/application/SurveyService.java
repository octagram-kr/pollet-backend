package com.octagram.pollet.survey.application;

import com.octagram.pollet.global.exception.BusinessException;
import com.octagram.pollet.survey.domain.model.Survey;
import com.octagram.pollet.survey.domain.status.SurveyErrorCode;
import com.octagram.pollet.survey.presentation.dto.response.SurveyDetailResponse;
import com.octagram.pollet.survey.presentation.dto.response.SurveyResponse;
import com.octagram.pollet.survey.presentation.dto.response.TagResponse;
import com.octagram.pollet.survey.domain.repository.SurveyRepository;
import com.octagram.pollet.survey.domain.repository.SurveyTagRepository;
import com.octagram.pollet.survey.domain.repository.TagRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.List;

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
        return SurveyDetailResponse.from(survey);
    }

    @Transactional(readOnly = true)
    public List<TagResponse> getTagsBySurveyId(Long surveyId) {
        return surveyTagRepository.findBySurveyId(surveyId).stream()
                .map(surveyTag -> TagResponse.from(surveyTag.getTag()))
                .toList();
    }

    @Transactional(readOnly = true)
    public long countActive() {
        LocalDateTime now = LocalDateTime.now();
        return surveyRepository.countActive(now);
    }

    @Transactional(readOnly = true)
    public Page<SurveyResponse> searchSurveys(String keyword, Pageable pageable) {
        return surveyRepository.findByTitleContainingIgnoreCase(keyword, pageable)
                .map(SurveyResponse::from);
    }
}
