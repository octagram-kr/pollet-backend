package com.octagram.pollet.member.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.octagram.pollet.member.domain.model.PointHistory;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
}
