package com.octagram.pollet.gifticon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.octagram.pollet.gifticon.domain.model.GifticonProduct;

public interface GifticonProductRepository extends JpaRepository<GifticonProduct, Long> {
}
