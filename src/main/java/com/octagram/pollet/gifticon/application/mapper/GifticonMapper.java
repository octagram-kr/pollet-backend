package com.octagram.pollet.gifticon.application.mapper;

import org.mapstruct.Mapper;

import com.octagram.pollet.gifticon.domain.model.GifticonProduct;
import com.octagram.pollet.gifticon.presentation.dto.response.GifticonProductInSurveyFilterResponse;
import com.octagram.pollet.gifticon.presentation.dto.response.GifticonProductInSurveyGetDetailResponse;

@Mapper
public interface GifticonMapper {

	GifticonProductInSurveyGetDetailResponse toSurveyGetDetailResponse(GifticonProduct product);
	GifticonProductInSurveyFilterResponse toSurveyFilterResponse(GifticonProduct product);
}
