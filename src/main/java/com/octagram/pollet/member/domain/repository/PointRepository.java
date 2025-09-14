package com.octagram.pollet.member.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.member.domain.model.Point;

public interface PointRepository extends JpaRepository<Point, Long>, PointRepositoryCustom {

	Optional<Point> findByMember(Member member);

	boolean existsByMember(Member member);
}
