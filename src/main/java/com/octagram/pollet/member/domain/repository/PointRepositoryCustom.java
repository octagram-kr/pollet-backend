package com.octagram.pollet.member.domain.repository;

import java.util.Optional;

import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.member.domain.model.Point;

public interface PointRepositoryCustom {

	Optional<Point> findByMemberForUpdate(Member m);

	Point findByMemberOrCreateWithLock(Member member, long amount);
}
