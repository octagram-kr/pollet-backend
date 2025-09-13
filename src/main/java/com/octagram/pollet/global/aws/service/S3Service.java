package com.octagram.pollet.global.aws.service;

import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.octagram.pollet.global.aws.status.S3ErrorCode;
import com.octagram.pollet.global.exception.BusinessException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

	private final S3Client s3Client;
	private final S3Presigner s3Presigner;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	private final String TEMP_FOLDER_PREFIX = "temp/";

	public String uploadToTemp(MultipartFile file) {
		if (file.isEmpty()) {
			throw new BusinessException(S3ErrorCode.FILE_EMPTY);
		}

		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String tempFileName = UUID.randomUUID() + "." + extension;
		String tempFileKey = TEMP_FOLDER_PREFIX + tempFileName;

		PutObjectRequest putObjectRequest = PutObjectRequest.builder()
			.bucket(bucket)
			.key(tempFileKey)
			.contentType(file.getContentType())
			.build();

		try {
			s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
		} catch (IOException e) {
			throw new BusinessException(S3ErrorCode.FILE_IO_EXCEPTION);
		}
		log.info("S3 Uploaded: {}", tempFileKey);

		return tempFileName;
	}

	public String moveFromTemp(String tempFileName, String newPath) {
		String tempFileKey = TEMP_FOLDER_PREFIX + tempFileName;

		CopyObjectRequest copyReq = CopyObjectRequest.builder()
			.sourceBucket(bucket)
			.sourceKey(tempFileKey)
			.destinationBucket(bucket)
			.destinationKey(newPath)
			.build();

		s3Client.copyObject(copyReq);
		log.info("S3 Moved: {} -> {}", tempFileKey, newPath);

		DeleteObjectRequest deleteReq = DeleteObjectRequest.builder()
			.bucket(bucket)
			.key(tempFileKey)
			.build();

		s3Client.deleteObject(deleteReq);
		log.info("S3 Deleted: {}", tempFileKey);

		return newPath;
	}

	public String getPresignedUrl(String objectKey, Duration duration) {
		if (objectKey == null || objectKey.isBlank()) {
			return null;
		}

		GetObjectRequest getReq = GetObjectRequest.builder()
			.bucket(bucket)
			.key(objectKey)
			.build();

		GetObjectPresignRequest presignReq = GetObjectPresignRequest.builder()
			.signatureDuration(duration)
			.getObjectRequest(getReq)
			.build();

		log.info("S3 Presigned URL Created: {} (duration: {})", objectKey, duration);
		PresignedGetObjectRequest presignedGetReq = s3Presigner.presignGetObject(presignReq);

		return presignedGetReq.url().toString();
	}
}
