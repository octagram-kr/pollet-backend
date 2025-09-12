package com.octagram.pollet.member.domain.repository;

import java.util.List;

import com.octagram.pollet.member.domain.model.MemberTag;

public interface MemberTagRepositoryCustom {
	List<MemberTag> findByMemberId(Long memberId);
}
