package com.octagram.pollet.board.domain.repository;

import java.util.List;

import com.octagram.pollet.board.domain.model.Post;

public interface PostRepositoryCustom {

	public List<Post> findAllQueryDsl();
}
