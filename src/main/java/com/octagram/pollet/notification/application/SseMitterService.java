package com.octagram.pollet.notification.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class SseMitterService {

	// SseEmiter 객체를 저장하는 Map 객체 (동시성 이슈를 고려하여 ConcurrentHashMap으로 작성)
	private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();
	private static final Long TIMEOUT = 60L * 1000 * 30; // 30분

	// 만약 연결 요청이 오면 새로운 SseEmitter 객체를 만들고 (이 때, 생성자의 파라미터에는 SseEmitter의 유효시간이 들어감)
	public SseEmitter createEmitter(String memberId) {
		SseEmitter emitter = new SseEmitter(TIMEOUT);
		// 그리고 해당 사용자에 맞는 emitter를 map에 저장하고,
		emitters.put(Long.valueOf(memberId), emitter);

		// 연결이 끝나거나 / 만료되거나 / 에러가 발생했을 때에는 해당 맵에서 삭제
		emitter.onCompletion(() -> emitters.remove(memberId));
		emitter.onTimeout(() -> emitters.remove(memberId));
		emitter.onError((error) -> emitters.remove(memberId));

		return emitter;
	}
}
