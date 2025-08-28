package com.octagram.pollet.member.infrastructure;

import java.util.Optional;

import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.member.domain.model.QMember;
import com.octagram.pollet.member.domain.model.type.Role;
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

	@Override
	public Optional<Member> findByMemberId(String memberId) {
		QMember member = QMember.member;
		return Optional.ofNullable(queryFactory
			.selectFrom(member)
			.where(
				member.memberId.eq(memberId),
				member.role.eq(Role.MEMBER)
			)
			.fetchOne());
	}
}
