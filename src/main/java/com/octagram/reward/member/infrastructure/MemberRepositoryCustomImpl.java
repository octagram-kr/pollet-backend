package com.octagram.reward.member.infrastructure;

import com.octagram.reward.member.domain.Member;
import com.octagram.reward.member.domain.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Member findByMemberNickname(String nickname) {
		QMember member = QMember.member;
		return queryFactory
			.selectFrom(member)
			.where(member.nickname.eq(nickname))
			.fetchOne();
	}
}
