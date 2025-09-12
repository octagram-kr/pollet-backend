package com.octagram.pollet.member.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.octagram.pollet.global.exception.BusinessException;
import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.member.domain.model.MemberTag;
import com.octagram.pollet.member.domain.model.type.Role;
import com.octagram.pollet.member.domain.repository.MemberRepository;
import com.octagram.pollet.member.domain.repository.MemberTagRepository;
import com.octagram.pollet.member.domain.status.MemberErrorCode;
import com.octagram.pollet.member.presentation.dto.request.MemberUpdateRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final MemberTagRepository memberTagRepository;

	@Transactional
	public void updateMember(String memberId, MemberUpdateRequest request) {
		Member member = findByMemberId(memberId);

		if (memberRepository.existsByNicknameAndIdNot(request.nickname(), member.getId())) {
			throw new BusinessException(MemberErrorCode.DUPLICATE_NICKNAME);
		}

		member.updateInfo(
			request.nickname().trim(),
			request.gender().trim(),
			request.yearOfBirth().trim(),
			request.job().trim(),
			request.phoneNumber().trim(),
			Role.MEMBER
		);
	}

	@Transactional(readOnly = true)
	public Member findByMemberId(String memberId) {
		return memberRepository.findByMemberId(memberId)
			.orElseThrow(() -> new BusinessException(MemberErrorCode.MEMBER_NOT_FOUND));
	}

	@Transactional(readOnly = true)
	public List<MemberTag> findMemberTags(String memberId) {
		Member member = findByMemberId(memberId);
		return memberTagRepository.findByMemberId(member.getId());
	}
}
