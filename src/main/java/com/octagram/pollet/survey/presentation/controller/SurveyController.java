package com.octagram.pollet.survey.presentation.controller;

import com.octagram.pollet.survey.domain.service.SurveyService;
import com.octagram.pollet.survey.dto.TagResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/v1/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping("/tags")
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<TagResponse> tags = surveyService.getAllTags();
        return ResponseEntity.ok(tags);
    }


}
