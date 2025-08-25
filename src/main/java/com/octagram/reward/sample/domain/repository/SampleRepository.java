package com.octagram.reward.sample.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.octagram.reward.sample.domain.model.Sample;

public interface SampleRepository extends JpaRepository<Sample, Long>, SampleRepositoryCustom {

}
