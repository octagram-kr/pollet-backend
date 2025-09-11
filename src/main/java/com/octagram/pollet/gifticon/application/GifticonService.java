package com.octagram.pollet.gifticon.application;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.octagram.pollet.gifticon.domain.model.GifticonProduct;
import com.octagram.pollet.gifticon.domain.repository.GifticonProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GifticonService {

	private final GifticonProductRepository gifticonProductRepository;

	public Optional<GifticonProduct> findProductById(Long id) {
		return gifticonProductRepository.findById(id);
	}
}
