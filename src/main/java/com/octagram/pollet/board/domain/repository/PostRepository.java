package com.octagram.pollet.board.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.octagram.pollet.board.domain.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
