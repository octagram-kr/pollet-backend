package com.octagram.pollet.member.infrastructure.persistence;

import static com.octagram.pollet.member.domain.model.QMemberTag.*;
import static com.octagram.pollet.survey.domain.model.QTag.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.octagram.pollet.member.domain.model.MemberTag;
import com.octagram.pollet.member.domain.repository.MemberTagRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberTagRepositoryImpl implements MemberTagRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<MemberTag> findByMemberId(Long memberId) {
		return queryFactory
			.selectFrom(memberTag)
			.leftJoin(memberTag.tag, tag).fetchJoin()
			.where(memberTag.member.id.eq(memberId))
			.orderBy(memberTag.completeCount.desc())
			.fetch();
	}
}
