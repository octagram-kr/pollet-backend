package com.octagram.pollet.board.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.octagram.pollet.board.domain.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
}
