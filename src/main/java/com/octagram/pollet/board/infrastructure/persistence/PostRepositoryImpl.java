package com.octagram.pollet.board.infrastructure.persistence;

import static com.octagram.pollet.board.domain.model.QBoard.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.octagram.pollet.board.domain.model.Post;
import com.octagram.pollet.board.domain.model.QPost;
import com.octagram.pollet.board.domain.repository.PostRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public List<Post> findAllQueryDsl() {
		QPost post = QPost.post;
		return queryFactory
			.selectFrom(post)
			.join(post.board, board)
			.fetchJoin()
			.fetch();
	}
}
