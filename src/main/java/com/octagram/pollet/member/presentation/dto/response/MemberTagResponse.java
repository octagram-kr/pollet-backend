package com.octagram.pollet.member.presentation.dto.response;

import com.octagram.pollet.survey.presentation.dto.response.standard.TagResponse;

public record MemberTagResponse(
	TagResponse tag,
	Long completeCount
) {
}
