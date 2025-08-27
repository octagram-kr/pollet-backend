package com.octagram.pollet.sample.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.octagram.pollet.sample.domain.model.Sample;
import com.octagram.pollet.sample.domain.repository.SampleRepository;
import com.octagram.pollet.support.annotation.ServiceTest;

@ServiceTest
class SampleServiceTest {

	private SampleRepository sampleRepository;
	private SampleService sampleService;

	@BeforeEach
	void setUp() {
		sampleRepository = mock(SampleRepository.class);
		sampleService = new SampleService(sampleRepository);
	}

	@Test
	@DisplayName("값으로 샘플을 생성했을 때 생성된 샘플을 반환하는 데 성공한다.")
	void createSample() {
		// given - 테스트를 위한 데이터 세팅
		String sampleValue = "sample";
		Sample sample = Sample.builder().id(1L).value(sampleValue).build();
		when(sampleRepository.save(any(Sample.class))).thenReturn(sample);

		// when - 테스트 하려는 동작
		Sample result = sampleService.createSample(sampleValue);

		// then - 결과 검증 (Assertion 사용)
		assertThat(result.getValue()).isEqualTo(sampleValue);
		verify(sampleRepository, times(1)).save(any(Sample.class));
	}

	@Test
	@DisplayName("존재하는 샘플을 아이디로 검색하는 데 성공한다.")
	void readSample() {
		// given
		Long targetId = 1L;
		String sampleValue = "sample";
		Optional<Sample> sample = Optional.ofNullable(Sample.builder().id(1L).value(sampleValue).build());
		when(sampleRepository.findByIdCustom(any(Long.class))).thenReturn(sample);

		// when
		Sample result = sampleService.readSample(targetId);

		// then
		assertThat(result.getId()).isEqualTo(targetId);
		assertThat(result.getValue()).isEqualTo(sampleValue);
		verify(sampleRepository, times(1)).findByIdCustom(any(Long.class));
	}
}