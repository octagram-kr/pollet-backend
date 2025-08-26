package com.octagram.pollet.sample.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.octagram.pollet.global.exception.BusinessException;
import com.octagram.pollet.sample.domain.model.Sample;
import com.octagram.pollet.sample.domain.repository.SampleRepository;
import com.octagram.pollet.sample.domain.status.SampleErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SampleService {

	private final SampleRepository sampleRepository;

	public Sample createSample(String value) {
		Sample sample = Sample.builder()
			.value(value)
			.build();
		try {
			return sampleRepository.save(sample);
		} catch (Exception e) {
			throw new BusinessException(SampleErrorCode.SAMPLE_CREATE_ERROR, e);
		}
	}

	public Sample readSample(Long id) {
		Optional<Sample> sample = sampleRepository.findByIdCustom(id);
		if (sample.isPresent()) return sample.get();
		throw new BusinessException(SampleErrorCode.SAMPLE_NOT_FOUND);
	}
}
