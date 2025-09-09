package com.octagram.pollet.survey.presentation.dto.request;

import java.util.List;

import com.octagram.pollet.survey.domain.model.type.FilterSortType;

public record SurveyFilterRequest(
	String keyword,
	List<String> genders,
	List<String> ages,
	List<String> jobs,
	List<String> tags,
	Long minPoint,
	Long maxPoint,
	Long estimatedTime,
	FilterSortType sortType,
	Boolean gifticon
) {
}
