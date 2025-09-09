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

	public void save(String memberId, String token) {
		String key = getRedisKey(memberId, token);

		redisTemplate.opsForValue().set(
			key,
			Boolean.TRUE.toString(),
			refreshTokenExpiration,
			TimeUnit.MILLISECONDS
		);
	}

	public boolean existsToken(String memberId, String token) {
		String key = getRedisKey(memberId, token);

		return redisTemplate.hasKey(key);
	}

	public void delete(String memberId, String token) {
		String key = getRedisKey(memberId, token);

		redisTemplate.delete(key);
	}

	private String getRedisKey(String memberId, String token) {
		return String.format(REDIS_REFRESH_TOKEN_KEY, memberId, token);
	}
}
