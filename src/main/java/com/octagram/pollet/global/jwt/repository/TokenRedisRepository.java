package com.octagram.pollet.global.jwt.repository;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TokenRedisRepository {

	private static final String REDIS_REFRESH_TOKEN_KEY = "auth:refresh-token:user:%s:token:%s";

	private final RedisTemplate<String, String> redisTemplate;

	@Value("${jwt.refresh.expiration}")
	public Long refreshTokenExpiration;

	public void save(String email, String token) {
		String key = getRedisKey(email, token);

		redisTemplate.opsForValue().set(
			key,
			Boolean.TRUE.toString(),
			refreshTokenExpiration,
			TimeUnit.MILLISECONDS
		);
	}

	public boolean existsToken(String email, String token) {
		String key = getRedisKey(email, token);

		return redisTemplate.hasKey(key);
	}

	public void delete(String email, String token) {
		String key = getRedisKey(email, token);

		redisTemplate.delete(key);
	}

	private String getRedisKey(String email, String token) {
		return String.format(REDIS_REFRESH_TOKEN_KEY, email, token);
	}
}
