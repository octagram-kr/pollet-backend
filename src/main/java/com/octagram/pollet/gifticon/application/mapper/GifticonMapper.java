package com.octagram.pollet.gifticon.application.mapper;

import org.mapstruct.Mapper;

import com.octagram.pollet.gifticon.domain.model.GifticonProduct;
import com.octagram.pollet.gifticon.presentation.dto.response.GifticonProductResponse;

@Mapper
public interface GifticonMapper {

	GifticonProductResponse toGifticonProductResponse(GifticonProduct gifticonProduct);
}
