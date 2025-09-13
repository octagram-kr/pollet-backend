package com.octagram.pollet.member.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.octagram.pollet.member.domain.model.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    Optional<Member> findByMemberId(String memberId);
    Optional<Member> findByEmail(String email);
	boolean existsByNicknameAndIdNot(String nickname, Long id);
}
