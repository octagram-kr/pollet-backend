package com.octagram.pollet.gifticon.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Table(name = "gifticon_product")
public class GifticonProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Long price;

	@Column(nullable = false)
	private String category; // TODO: ENUM 분리 필요

	@Column(nullable = true)
	private String unlockGrade; // TODO: 등급 별도 엔티티 분리 필요

	@Column(nullable = true)
	private String imageUrl;

	@Column(nullable = false)
	private String status; // TODO: ENUM 분리 필요
}
