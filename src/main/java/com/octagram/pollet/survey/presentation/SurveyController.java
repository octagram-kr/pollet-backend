package com.octagram.pollet.survey.presentation;

import com.octagram.pollet.global.aws.service.S3Service;
import com.octagram.pollet.global.dto.ApiResponse;
import com.octagram.pollet.member.application.MemberService;
import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.survey.application.SurveyService;
import com.octagram.pollet.survey.domain.status.SurveySuccessCode;
import com.octagram.pollet.survey.presentation.dto.request.SurveyCreateRequest;
import com.octagram.pollet.survey.presentation.dto.request.SurveyFilterRequest;
import com.octagram.pollet.survey.presentation.dto.request.SurveySubmissionRequest;
import com.octagram.pollet.survey.presentation.dto.response.*;
import com.octagram.pollet.survey.presentation.dto.response.SurveyImageGetResponse;
import com.octagram.pollet.survey.presentation.dto.response.SurveyImageUploadResponse;
import com.octagram.pollet.survey.presentation.dto.response.standard.QuestionResponse;
import com.octagram.pollet.survey.presentation.dto.response.standard.SurveyResponse;
import com.octagram.pollet.survey.presentation.dto.response.standard.TagResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/surveys")
@RequiredArgsConstructor
public class SurveyController {

	private final SurveyService surveyService;
	private final S3Service s3Service;
	private final MemberService memberService;

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
	public ApiResponse<SurveyResponse> getSurveyById(@PathVariable Long surveyId) {
		SurveyResponse survey = surveyService.getSurveyById(surveyId);
		return ApiResponse.success(SurveySuccessCode.READ_SURVEY_DETAIL_SUCCESS, survey);
	}

	@GetMapping("/{surveyId}/tags")
	@Operation(summary = "설문조사 태그 조회", description = "특정 설문조사에 연결된 태그를 조회합니다.")
	public ApiResponse<List<TagResponse>> getTagsBySurveyId(@PathVariable Long surveyId) {
		List<TagResponse> tags = surveyService.getTagsBySurveyId(surveyId);
		return ApiResponse.success(SurveySuccessCode.READ_SURVEY_TAGS_SUCCESS, tags);
	}

	@GetMapping("/count")
	@Operation(summary = "전체 설문조사의 수 조회", description = "등록된 전체 설문조사의 수를 조회합니다.")
	public ApiResponse<Long> getActiveSurveys() {
		Long surveyCount = surveyService.getActiveCount();
		return ApiResponse.success(SurveySuccessCode.READ_SURVEY_COUNT_SUCCESS, surveyCount);
	}

	@GetMapping("/recent")
	@Operation(summary = "최근 등록된 설문조사 조회", description = "가장 최근에 등록된 설문조사 4개를 생성일 기준 내림차순으로 조회합니다.")
	public ApiResponse<List<SurveyGetRecentResponse>> getLatest4Surveys() {
		List<SurveyGetRecentResponse> newSurveys = surveyService.getLatest4Surveys();
		return ApiResponse.success(SurveySuccessCode.READ_RECENT_SURVEYS_SUCCESS, newSurveys);
	}

	@GetMapping("/recent/targetQuestion")
	@Operation(summary = "최근 등록된 설문조사 조회", description = "가장 최근에 등록된 설문조사 4개의 대상자 판별 질문을 조회합니다.")
	public ApiResponse<List<TargetQuestionResponse>> getLatest4SurveysTargetQuestions() {
		List<TargetQuestionResponse> newSurveyQuestion = surveyService.getLatest4SurveysTargetQuestions();
		return ApiResponse.success(SurveySuccessCode.READ_RECENT_REPRESENTATIVE_QUESTIONS_SUCCESS, newSurveyQuestion);
	}

	@PostMapping(value = "/upload-temp-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(summary = "설문 생성 중 임시 이미지 업로드", description = "이미지를 임시 경로에 업로드 합니다.")
	public ApiResponse<SurveyImageUploadResponse> uploadImage(@RequestPart("image") MultipartFile image) {
		String tempFileName = s3Service.uploadToTemp(image);
		return ApiResponse.success(new SurveyImageUploadResponse(tempFileName));
	}

	@GetMapping("/get-temp-image")
	@Operation(summary = "임시 경로에 존재하는 이미지 URL 조회", description = "임시 경로에 존재하는 이미지의 5분 제한 URL을 조회합니다.")
	public ApiResponse<SurveyImageGetResponse> getImage(@RequestParam("key") String key) {
		String tempFileUrl = s3Service.getPresignedUrl(key, Duration.ofMinutes(5));
		return ApiResponse.success(new SurveyImageGetResponse(tempFileUrl));
	}

	@GetMapping
	@Operation(summary = "설문조사 목록 조회 (필터링)", description = "다양한 조건으로 설문조사를 필터링하여 조회합니다.")
	public ApiResponse<List<SurveyResponse>> getSurveysByFilter(
		@ModelAttribute SurveyFilterRequest request,
		@PageableDefault(size = 12) Pageable pageable
	) {
		List<SurveyResponse> surveys = surveyService.filterSurveys(request, pageable);
		return ApiResponse.success(surveys);
	}

	@GetMapping("/{surveyId}/questions")
	@Operation(summary = "특정 설문조사의 문항 조회 (선택지 포함)", description = "설문조사 아이디로 문항과 문항 선택지를 조회합니다.")
	public ApiResponse<List<QuestionResponse>> getSurveyQuestions(@PathVariable Long surveyId) {
		List<QuestionResponse> result = surveyService.getQuestions(surveyId);
		return ApiResponse.success(result);
	}

	@PostMapping("/{surveyId}/submissions")
	@Operation(summary = "설문 응답 제출", description = "진행한 설문조사의 응답을 제출합니다.")
	public ApiResponse<Void> submitSurvey(
		@PathVariable Long surveyId,
		@AuthenticationPrincipal String memberId,
		@RequestBody @Valid SurveySubmissionRequest request
	) {
		Member member = memberService.findByMemberId(memberId);
		surveyService.submitSurvey(surveyId, member, request);
		return ApiResponse.success(SurveySuccessCode.CREATE_SURVEY_SUBMISSION_SUCCESS);
	}

	@PostMapping
	@Operation(summary = "설문조사 생성", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
		content = @Content(
			mediaType = "application/json",
			schema = @Schema(implementation = SurveyResponse.class),
			examples = @ExampleObject(
				name = "설문조사 생성 DTO 예시",
				value = """
					{
						"coverUrl": null,
						"title": "대학생 취업 준비 현황과 지원 필요도 조사",
						"purpose": "연구 과제",
						"startDateTime": "2025-09-10T19:02:33.170Z",
						"endDateTime": "2025-09-10T19:02:33.170Z",
						"endCondition": "END_BY_DATE",
						"submissionExpireDate": "2025-09-10",
						"description": "안녕하세요, 팀 Octagram 입니다.\\n본 설문은...",
						"privacyType": "AGREEMENT_FOR_COLLECTION_AND_USE",
						"privacyPurposeValue": "추후 인터뷰 모집",
						"privacyContents": 12,
						"privacyExpireDate": "2025-09-10",
						"primaryColor": "0x51D0B6",
						"secondaryColor": "0xF7F7F7",
						"targetGender": null,
						"targetAge": "20대",
						"targetJob": "고등학생",
						"tags": [
							"AI",
							"교육"
						],
						"questions": [
							{
								"page": 0,
								"order": 0,
								"title": "질문을 작성해주세요",
								"description": null,
								"type": "MULTIPLE_CHOICE",
								"isRequired": true,
								"isCheckTarget": true,
								"isCheckDiligent": false,
								"imageUrl": null,
								"options": [
									{
										"order": 1,
										"content": "질문지 작성란입니다",
										"imageUrl": null,
										"isCheckTarget": true,
										"isCheckDiligent": false
									},
									{
										"order": 2,
										"content": "질문지 작성란입니다",
										"imageUrl": null,
										"isCheckTarget": true,
										"isCheckDiligent": false
									},
									{
										"order": 3,
										"content": "질문지 작성란입니다",
										"imageUrl": null,
										"isCheckTarget": false,
										"isCheckDiligent": false
									}
								]
							},
							{
								"page": 1,
								"order": 1,
								"title": "현재 귀하의 학적 상태를 선택해주세요",
								"description": null,
								"type": "SINGLE_CHOICE",
								"isRequired": false,
								"isCheckTarget": false,
								"isCheckDiligent": false,
								"imageUrl": null,
								"options": [
									{
										"order": 1,
										"content": "재학 중",
										"imageUrl": null,
										"isCheckTarget": false,
										"isCheckDiligent": false
									},
									{
										"order": 2,
										"content": "휴학 중",
										"imageUrl": null,
										"isCheckTarget": false,
										"isCheckDiligent": false
									},
									{
										"order": 3,
										"content": "졸업 예정",
										"imageUrl": null,
										"isCheckTarget": false,
										"isCheckDiligent": false
									},
									{
										"order": 4,
										"content": "졸업",
										"imageUrl": null,
										"isCheckTarget": false,
										"isCheckDiligent": false
									}
								]
							},
							{
								"page": 1,
								"order": 2,
								"title": "취업 준비 시 가장 많이 활용하는 정보 채널은 무엇인가요?",
								"description": null,
								"type": "MULTIPLE_CHOICE",
								"isRequired": false,
								"isCheckTarget": false,
								"isCheckDiligent": false,
								"imageUrl": null,
								"options": [
									{
										"order": 1,
										"content": "취업 포털 사이트",
										"imageUrl": null,
										"isCheckTarget": false,
										"isCheckDiligent": false
									},
									{
										"order": 2,
										"content": "학교 취업지원센터",
										"imageUrl": null,
										"isCheckTarget": false,
										"isCheckDiligent": false
									},
									{
										"order": 3,
										"content": "SNS/커뮤니티",
										"imageUrl": null,
										"isCheckTarget": false,
										"isCheckDiligent": false
									},
									{
										"order": 4,
										"content": "기타",
										"imageUrl": null,
										"isCheckTarget": false,
										"isCheckDiligent": false
									}
								]
							},
							{
								"page": 1,
								"order": 3,
								"title": "현재 가장 집중하고 있는 취업 준비 활동은 무엇인가요?",
								"description": "현재 가장 많은 시간을 들이고 있는 취업 준비 활동을 한 두 단어로 간단히 작성해 주세요.",
								"type": "SHORT_ANSWER",
								"isRequired": false,
								"isCheckTarget": false,
								"isCheckDiligent": false,
								"imageUrl": null,
								"options": []
							},
							{
								"page": 1,
								"order": 4,
								"title": "본인이 원하는 이상적인 취업 지원 서비스나 프로그램이 있다면 자유롭게 설명해 주세요.",
								"description": "기존에 경험했거나 부족하다고 느낀 지원 제도와 비교해 구체적으로 설명해 주셔도 좋습니다.",
								"type": "LONG_ANSWER",
								"isRequired": false,
								"isCheckTarget": false,
								"isCheckDiligent": false,
								"imageUrl": null,
								"options": []
							}
						],
						"rewardType": "POINT",
						"requireSubmissionCount": 150,
						"estimatedTime": 3,
						"rewardPoint": 480,
						"rewardGifticonProductId": 1,
						"rewardGifticonProductCount": 15
					}
					"""
			)
		)
	))
	public ApiResponse<?> createSurvey(@AuthenticationPrincipal String memberId, @RequestBody @Valid SurveyCreateRequest request) {
		surveyService.createSurvey(memberId, request);
		return ApiResponse.success(SurveySuccessCode.CREATE_SURVEY_SUCCESS);
	}

	@GetMapping("{surveyId}/results")
	@Operation(summary = "설문조사 전체 결과 조회(문항별 응답 통계 조회)", description = "특정 설문조사의 전체 결과를 조회합니다.(특정 설문조사의 각 문항별 응답 통계를 조회합니다.)")
	public ApiResponse<Slice<QuestionStatisticsResponse>> getSurveyResults(
			@PathVariable Long surveyId,
			@PageableDefault(size = 10) Pageable pageable

	) {
		Slice<QuestionStatisticsResponse> result = surveyService.getSurveyResults(surveyId, pageable);
		return ApiResponse.success(result);
	}
}
