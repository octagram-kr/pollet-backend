package com.octagram.pollet.survey.presentation;

import com.octagram.pollet.global.dto.ApiResponse;
import com.octagram.pollet.survey.application.SurveyService;
import com.octagram.pollet.survey.domain.status.SurveySuccessCode;
import com.octagram.pollet.survey.presentation.dto.response.SurveyGetDetailResponse;
import com.octagram.pollet.survey.presentation.dto.response.SurveyGetResponse;
import com.octagram.pollet.survey.presentation.dto.response.TagGetResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/v1/surveys")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping("/tags")
    @Operation(summary = "모든 태그 조회", description = "등록 가능한 모든 태그를 조회합니다.")
    public ApiResponse<List<TagGetResponse>> getAllTags() {
        List<TagGetResponse> tags = surveyService.getAllTags();
        return ApiResponse.success(SurveySuccessCode.READ_TAGS_SUCCESS, tags);
    }

    @GetMapping("/usedTags")
    @Operation(summary = "설문조사에 등록된 태그 조회", description = "중복 제거된 설문조사 태그를 조회합니다.")
    public ApiResponse<List<TagGetResponse>> getAllUsedTags() {
        List<TagGetResponse> tags = surveyService.getAllUsedTags();
        return ApiResponse.success(SurveySuccessCode.READ_SURVEY_TAGS_SUCCESS, tags);
    }

    @GetMapping("/{surveyId}")
    @Operation(summary = "설문조사 상세 정보 조회", description = "설문조사 상세정보를 조회합니다.")
    public ApiResponse<SurveyGetDetailResponse> getSurveyById(@PathVariable Long surveyId) {
        SurveyGetDetailResponse SurveyDetail = surveyService.getSurveyById(surveyId);
        return ApiResponse.success(SurveySuccessCode.READ_SURVEY_DETAIL_SUCCESS, SurveyDetail);
    }

    @GetMapping("/{surveyId}/tags")
    @Operation(summary = "설문조사 태그 조회", description = "특정 설문조사에 연결된 태그를 조회합니다.")
    public ApiResponse<List<TagGetResponse>> getTagsBySurveyId(@PathVariable Long surveyId) {
        List<TagGetResponse> tags = surveyService.getTagsBySurveyId(surveyId);
        return ApiResponse.success(SurveySuccessCode.READ_SURVEY_TAGS_SUCCESS, tags);
    }

    @GetMapping("/count")
    @Operation(summary = "전체 설문조사의 수 조회", description = "등록된 전체 설문조사의 수를 조회합니다.")
    public ApiResponse<Long> getActiveSurveys() {
        Long surveyCount = surveyService.getActiveCount();
        return ApiResponse.success(SurveySuccessCode.READ_SURVEY_COUNT_SUCCESS, surveyCount);
    }

    @GetMapping("/search")
    @Operation(summary = "키워드 기반 설문조사 검색", description = "키워드로 설문조사를 검색하고 페이징 처리된 결과를 반환합니다.")
    public ApiResponse<Page<SurveyGetResponse>> searchSurveys(
            @RequestParam String keyword,
            @PageableDefault(size = 12) Pageable pageable) {
        Page<SurveyGetResponse> surveys = surveyService.searchSurveys(keyword, pageable);
        return ApiResponse.success(SurveySuccessCode.SEARCH_SURVEYS_SUCCESS, surveys);
    }
}
