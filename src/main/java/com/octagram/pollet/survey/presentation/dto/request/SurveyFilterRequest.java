package com.octagram.pollet.survey.presentation.dto.request;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;

import com.octagram.pollet.survey.domain.model.type.FilterSortType;

import io.swagger.v3.oas.annotations.media.Schema;

@ParameterObject
@Schema(description = "설문 필터링 요청 DTO")
public record SurveyFilterRequest(

	@Schema(description = "제목 키워드")
	String keyword,

	@Schema(description = "성별")
	List<String> genders,

	@Schema(description = "나이대 (띄어쓰기는 '_' 문자로 대체)")
	List<String> ages,

	@Schema(description = "직업")
	List<String> jobs,

	@Schema(description = "태그")
	List<String> tags,

	@Schema(description = "최소 포인트")
	Long minPoint,

	@Schema(description = "최대 포인트")
	Long maxPoint,

	@Schema(description = "예상 소요시간")
	Long estimatedTime,

	@Schema(description = "정렬 타입")
	FilterSortType sortType,

	@Schema(description = "기프티콘 포함 여부", defaultValue = "true")
	Boolean gifticon
) {
}
