package com.octagram.reward.sample.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.octagram.reward.sample.domain.model.Sample;
import com.octagram.reward.global.dto.ApiResponse;
import com.octagram.reward.sample.domain.service.SampleService;
import com.octagram.reward.sample.domain.status.SampleSuccessCode;
import com.octagram.reward.sample.presentation.dto.request.CreateSampleRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/sample")
@RequiredArgsConstructor
public class SampleController {

	private final SampleService sampleService;

	@PostMapping
	public ApiResponse<Sample> createSample(@RequestBody @Valid CreateSampleRequest request) {
		Sample sample = sampleService.createSample(request.value());
		return ApiResponse.success(sample);
	}

	@GetMapping
	public ApiResponse<Sample> readSample(@RequestParam Long id) {
		Sample sample = sampleService.readSample(id);
		return ApiResponse.success(SampleSuccessCode.SAMPLE_READ_SUCCESS, sample);
	}
}
