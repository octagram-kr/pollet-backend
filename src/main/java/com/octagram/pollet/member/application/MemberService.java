package com.octagram.pollet.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.member.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	@Transactional
	public Member createMember(String nickname) {
		Member member = Member.builder()
			.nickname(nickname)
			.build();
		return memberRepository.save(member);
	}

	@Transactional
	public Member readMember(String nickname) {
		return memberRepository.findByMemberNickname(nickname);
	}
}
