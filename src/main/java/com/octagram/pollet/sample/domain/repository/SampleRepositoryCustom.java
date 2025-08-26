package com.octagram.pollet.sample.domain.repository;

import java.util.Optional;

import com.octagram.pollet.sample.domain.model.Sample;

public interface SampleRepositoryCustom {
	Optional<Sample> findByIdCustom(Long id);
}
