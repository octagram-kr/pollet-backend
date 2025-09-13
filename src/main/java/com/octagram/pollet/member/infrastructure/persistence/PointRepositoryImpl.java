package com.octagram.pollet.member.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.member.domain.model.Point;
import com.octagram.pollet.member.domain.model.QPoint;
import com.octagram.pollet.member.domain.repository.PointRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PointRepositoryImpl implements PointRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	private final EntityManager entityManager;

	@Override
	public Point findByMemberOrCreateWithLock(Member member, long amount) {
		Point point = findByMemberForUpdate(member)
			.orElse(null);

		if (point != null) {
			return point;
		}

		Point createdPoint = Point.builder()
			.member(member)
			.balance(amount)
			.build();
		entityManager.persist(createdPoint);
		entityManager.flush();

		return createdPoint;
	}

	@Override
	public Optional<Point> findByMemberForUpdate(Member member) {
		QPoint point = QPoint.point;

		return Optional.ofNullable(
			queryFactory.selectFrom(point)
				.where(point.member.eq(member))
				.setLockMode(LockModeType.PESSIMISTIC_WRITE)
				.fetchOne()
		);
	}
}
