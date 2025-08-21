package com.octagram.reward.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.octagram.reward.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
