package com.octagram.pollet.member.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import com.octagram.pollet.member.domain.repository.MemberRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

	private final JPAQueryFactory queryFactory;
}
