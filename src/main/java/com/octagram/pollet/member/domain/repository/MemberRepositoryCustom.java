package com.octagram.pollet.member.domain.repository;

import com.octagram.pollet.member.domain.model.Member;

public interface MemberRepositoryCustom {

	Member findByMemberNickname(String nickname);
}
