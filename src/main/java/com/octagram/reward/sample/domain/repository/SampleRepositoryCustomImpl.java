package com.octagram.reward.sample.domain.repository;

import java.util.Optional;

import com.octagram.reward.sample.domain.model.Sample;
import com.octagram.reward.sample.domain.model.QSample;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SampleRepositoryCustomImpl implements SampleRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<Sample> findByIdCustom(Long id) {
		QSample sample = QSample.sample;
		return Optional.ofNullable(queryFactory
			.selectFrom(sample)
			.where(sample.id.eq(id))
			.fetchOne());
	}
}
