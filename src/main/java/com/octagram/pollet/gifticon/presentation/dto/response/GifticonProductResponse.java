package com.octagram.pollet.gifticon.presentation.dto.response;

import com.octagram.pollet.gifticon.domain.model.type.GifticonProductStatus;
import com.octagram.pollet.member.domain.model.type.MemberGrade;

public record GifticonProductResponse(
	Long id,
	String name,
	Long price,
	String category,
	MemberGrade unlockGrade,
	String imageUrl,
	GifticonProductStatus status
) {
}
