package com.octagram.pollet.global.jwt.service;

import java.time.Duration;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import com.octagram.pollet.global.exception.BusinessException;
import com.octagram.pollet.global.jwt.status.JwtErrorCode;
import com.octagram.pollet.member.domain.repository.MemberRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.access.expiration}")
	private Long accessTokenExpiration;

	@Value("${jwt.refresh.expiration}")
	public Long refreshTokenExpiration;

	@Value("${jwt.access.header}")
	private String accessHeader;

	@Value("${jwt.refresh.header}")
	private String refreshHeader;

	private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
	private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
	private static final String MEMBER_ID_CLAIM = "memberId";
	private static final String EMAIL_CLAIM = "email";
	private static final String ROLE_CLAIM = "role";
	private static final String BEARER = "Bearer ";

	private final MemberRepository memberRepository;

	public String createAccessToken(String memberId, String email, String role) {
		Date now = new Date();
		return Jwts.builder()
			.subject(ACCESS_TOKEN_SUBJECT)
			.claim(MEMBER_ID_CLAIM, memberId)
			.claim(EMAIL_CLAIM, email)
			.claim(ROLE_CLAIM, role)
			.issuedAt(now)
			.expiration(new Date(now.getTime() + accessTokenExpiration))
			.signWith(getSecretKey())
			.compact();
	}

	public String createRefreshToken(String memberId) {
		Date now = new Date();
		return Jwts.builder()
			.subject(REFRESH_TOKEN_SUBJECT)
			.claim(MEMBER_ID_CLAIM, memberId)
			.issuedAt(now)
			.expiration(new Date(now.getTime() + refreshTokenExpiration))
			.signWith(getSecretKey())
			.compact();
	}

	public void sendAccessToken(HttpServletResponse response, String accessToken) {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setHeader(accessHeader, BEARER + accessToken);
	}

	public void setRefreshToken(HttpServletResponse response, String refreshToken) {
		ResponseCookie refreshTokenCookie = ResponseCookie.from(REFRESH_TOKEN_SUBJECT, refreshToken)
			.httpOnly(true)
			.secure(true)
			.sameSite("None")
			.path("/")
			.maxAge(Duration.ofMillis(refreshTokenExpiration))
			.build();

		response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
	}

	public void setExpiredRefreshToken(HttpServletResponse response) {
		ResponseCookie expiredCookie = ResponseCookie.from("RefreshToken", "")
			.httpOnly(true)
			.secure(true)
			.sameSite("None")
			.path("/")
			.maxAge(0)
			.build();

		response.addHeader(HttpHeaders.SET_COOKIE, expiredCookie.toString());
	}

	public Optional<String> extractAccessToken(HttpServletRequest request) {
		String header = request.getHeader(accessHeader);
		if (header != null && header.startsWith(BEARER)) {
			return Optional.of(header.substring(BEARER.length()));
		}

		return Optional.empty();
	}

	public Optional<String> extractRefreshToken(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(REFRESH_TOKEN_SUBJECT)) {
					return Optional.of(cookie.getValue());
				}
			}
		}

		return Optional.empty();
	}

	public void validateToken(String token) {
		try {
			Claims claims = getClaims(token);

			String memberId = claims.get(MEMBER_ID_CLAIM, String.class);

			memberRepository.findByMemberId(memberId)
				.orElseThrow(() -> new BusinessException(JwtErrorCode.TOKEN_INVALID));
		} catch (JwtException e) {
			throw new BusinessException(JwtErrorCode.TOKEN_INVALID);
		}
	}

	public Optional<String> getMemberIdFromToken(String token) {
		try {
			Claims claims = getClaims(token);

			return Optional.ofNullable(claims.get(MEMBER_ID_CLAIM, String.class));
		} catch (JwtException | IllegalArgumentException e) {
			return Optional.empty();
		}
	}

	public Optional<String> getRoleFromToken(String token) {
		try {
			Claims claims = getClaims(token);

			return Optional.ofNullable(claims.get(ROLE_CLAIM, String.class));
		} catch (JwtException | IllegalArgumentException e) {
			return Optional.empty();
		}
	}

	public boolean validateRefreshToken(String token) {
		try {
			Jwts
				.parser()
				.verifyWith(getSecretKey())
				.build()
				.parseSignedClaims(token);
			return true;
		} catch (JwtException e) {
			throw new BusinessException(JwtErrorCode.REFRESH_TOKEN_INVALID);
		}
	}

	private Claims getClaims(String token) {
		return Jwts
			.parser()
			.verifyWith(getSecretKey())
			.build()
			.parseSignedClaims(token)
			.getPayload();
	}

	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
	}
}
