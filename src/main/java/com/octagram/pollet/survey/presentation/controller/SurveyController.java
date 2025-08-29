package com.octagram.pollet.survey.presentation.controller;

import com.octagram.pollet.survey.domain.service.SurveyService;
import com.octagram.pollet.survey.dto.TagResponse;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "모든 태그 조회", description = "등록 가능한 모든 태그를 조회합니다.")
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<TagResponse> tags = surveyService.getAllTags();
        return ResponseEntity.ok(tags);
    }

    // 설문조사에 사용된 태그 중복 제거 후 조회
    @GetMapping("/usedTags")
    @Operation(summary = "설문조사에서 사용된 태그 조회", description = "중복 제거된 설문조사 태그를 조회합니다.")
    public ResponseEntity<List<TagResponse>> getAllUsedTags() {
        List<TagResponse> tags = surveyService.getAllUsedTags();
        return ResponseEntity.ok(tags);
    }


}
