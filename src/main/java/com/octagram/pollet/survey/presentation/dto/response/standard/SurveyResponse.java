package com.octagram.pollet.survey.presentation.dto.response.standard;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.octagram.pollet.gifticon.presentation.dto.response.GifticonProductResponse;
import com.octagram.pollet.survey.domain.model.type.EndCondition;
import com.octagram.pollet.survey.domain.model.type.PrivacyType;
import com.octagram.pollet.survey.domain.model.type.RewardType;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "정보 응답 DTO")
public record SurveyResponse(

	@Schema(description = "ID")
	Long id,

	@Schema(description = "제목")
	String title,

	@Schema(description = "부제목")
	String subtitle,

	@Schema(description = "설명")
	String description,

	@Schema(description = "커버 이미지 URL")
	String coverUrl,

	@Schema(description = "대표 색")
	String primaryColor,

	@Schema(description = "보조 색")
	String secondaryColor,

	@Schema(description = "생성자 이름 (멤버 정보 X)")
	String creatorName,

	@Schema(description = "목적")
	String purpose,

	@Schema(description = "시작 시간")
	LocalDateTime startDateTime,

	@Schema(description = "종료 조건")
	EndCondition endCondition,

	@Schema(description = "종료 시간")
	LocalDateTime endDateTime,

	@Schema(description = "템플릿 여부")
	Boolean isTemplate,

	@Schema(description = "응답 보유 만료일")
	LocalDate submissionExpireDate,

	@Schema(description = "개인정보 동의 유형")
	PrivacyType privacyType,

	@Schema(description = "개인정보 수집 항목")
	Long privacyContents,

	@Schema(description = "개인정보 수집 목적 유형")
	Long privacyPurposeType,

	@Schema(description = "개인정보 수집 목적 상세")
	String privacyPurposeValue,

	@Schema(description = "개인정보 보유 만료일")
	LocalDate privacyExpireDate,

	@Schema(description = "응답 예상 소요 시간")
	Long estimatedTime,

	@Schema(description = "필요 응답 수")
	Long requireSubmissionCount,

	@Schema(description = "현재 응답 수")
	Long currentSubmissionCount,

	@Schema(description = "보상 유형")
	RewardType rewardType,

	@Schema(description = "보상 포인트")
	Long rewardPointPerMinute,

	@Schema(description = "예치 포인트")
	Long availablePoint,

	@Schema(description = "보상 기프티콘 상품")
	GifticonProductResponse gifticonProduct,

	@Schema(description = "태그 목록")
	List<TagResponse> surveyTags,

	@Schema(description = "생성일")
	LocalDateTime createdAt,

	@Schema(description = "수정일")
	LocalDateTime updatedAt
) {
}
