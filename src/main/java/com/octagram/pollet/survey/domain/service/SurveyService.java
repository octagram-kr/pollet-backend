package com.octagram.pollet.survey.domain.service;

import com.octagram.pollet.survey.domain.model.SurveyTag;
import com.octagram.pollet.survey.dto.TagResponse;
import com.octagram.pollet.survey.repository.SurveyTagRepository;
import com.octagram.pollet.survey.repository.TagRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SurveyService {
    private final TagRepository tagRepository;
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









}
