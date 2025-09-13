package com.octagram.pollet.member.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.octagram.pollet.member.domain.model.MemberTag;

public interface MemberTagRepository extends JpaRepository<MemberTag, Long>, MemberTagRepositoryCustom {
}
