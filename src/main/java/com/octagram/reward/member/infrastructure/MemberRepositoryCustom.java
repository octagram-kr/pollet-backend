package com.octagram.reward.member.infrastructure;

import com.octagram.reward.member.domain.Member;

public interface MemberRepositoryCustom {

	Member findByMemberNickname(String nickname);
}
