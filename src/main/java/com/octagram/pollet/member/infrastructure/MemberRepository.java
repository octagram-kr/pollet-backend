package com.octagram.pollet.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.octagram.pollet.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
