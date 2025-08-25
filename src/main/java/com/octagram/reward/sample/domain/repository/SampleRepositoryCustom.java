package com.octagram.reward.sample.domain.repository;

import java.util.Optional;

import com.octagram.reward.sample.domain.model.Sample;

public interface SampleRepositoryCustom {
	Optional<Sample> findByIdCustom(Long id);
}
