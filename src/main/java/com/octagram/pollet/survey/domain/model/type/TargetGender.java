package com.octagram.pollet.survey.domain.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TargetGender {
	MALE("남자"),
	FEMALE("여자"),
	ANY("무관");

	private final String displayName;
}
