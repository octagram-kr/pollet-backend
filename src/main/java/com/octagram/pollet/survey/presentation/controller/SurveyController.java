package com.octagram.pollet.survey.presentation.controller;

import com.octagram.pollet.global.dto.ApiResponse;
import com.octagram.pollet.survey.domain.service.SurveyService;
import com.octagram.pollet.survey.domain.status.SurveySuccessCode;
import com.octagram.pollet.survey.presentation.dto.response.SurveyDetailResponse;
import com.octagram.pollet.survey.presentation.dto.response.TagResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/v1/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping("/tags")
    @Operation(summary = "모든 태그 조회", description = "등록 가능한 모든 태그를 조회합니다.")
    public ApiResponse<List<TagResponse>> getAllTags() {
        List<TagResponse> tags = surveyService.getAllTags();
        return ApiResponse.success(SurveySuccessCode.READ_TAGS_SUCCESS, tags);
    }

    @GetMapping("/usedTags")
    @Operation(summary = "설문조사에 등록된 태그 조회", description = "중복 제거된 설문조사 태그를 조회합니다.")
    public ApiResponse<List<TagResponse>> getAllUsedTags() {
        List<TagResponse> tags = surveyService.getAllUsedTags();
        return ApiResponse.success(SurveySuccessCode.READ_SURVEY_TAGS_SUCCESS, tags);
    }

    @GetMapping("/{surveyId}")
    @Operation(summary = "설문조사 상세 정보 조회", description = "설문조사 상세정보를 조회합니다.")
    public ApiResponse<SurveyDetailResponse> getSurveyById(@PathVariable Long surveyId) {
        SurveyDetailResponse SurveyDetail = surveyService.getSurveyById(surveyId);
        return ApiResponse.success(SurveySuccessCode.READ_SURVEY_DETAIL_SUCCESS, SurveyDetail);
    }
}
