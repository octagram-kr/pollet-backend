package com.octagram.pollet.member.infrastructure.persistence;

import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.member.domain.model.QMember;
import com.octagram.pollet.member.domain.repository.MemberRepositoryCustom;
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
