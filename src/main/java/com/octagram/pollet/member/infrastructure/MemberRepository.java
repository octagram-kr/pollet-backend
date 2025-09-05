package com.octagram.pollet.member.infrastructure;

import com.octagram.pollet.member.domain.model.type.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import com.octagram.pollet.member.domain.model.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    Optional<Member> findByMemberIdAndRole(String memberId, Role role);

    Optional<Member> findByEmail(String email);
}
