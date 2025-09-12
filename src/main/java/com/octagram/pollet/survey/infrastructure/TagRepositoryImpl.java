package com.octagram.pollet.survey.infrastructure;

import static com.octagram.pollet.member.domain.model.QMemberTag.*;
import static com.octagram.pollet.survey.domain.model.QTag.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.octagram.pollet.survey.domain.model.Tag;
import com.octagram.pollet.survey.domain.repository.TagRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Tag> findByMemberId(Long memberId) {
		return queryFactory
			.select(tag)
			.from(memberTag)
			.where(memberTag.member.id.eq(memberId))
			.fetch();
	}
}
