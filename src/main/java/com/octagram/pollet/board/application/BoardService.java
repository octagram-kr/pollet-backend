package com.octagram.pollet.board.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.octagram.pollet.board.application.mapper.BoardMapper;
import com.octagram.pollet.board.application.mapper.PostMapper;
import com.octagram.pollet.board.domain.model.Board;
import com.octagram.pollet.board.domain.model.Post;
import com.octagram.pollet.board.domain.repository.BoardRepository;
import com.octagram.pollet.board.domain.repository.PostRepository;
import com.octagram.pollet.board.domain.status.BoardErrorCode;
import com.octagram.pollet.board.presentation.dto.request.BoardCreateRequest;
import com.octagram.pollet.board.presentation.dto.request.PostCreateRequest;
import com.octagram.pollet.board.presentation.dto.response.BoardCreateResponse;
import com.octagram.pollet.board.presentation.dto.response.BoardFindAllResponse;
import com.octagram.pollet.board.presentation.dto.response.PostCreateResponse;
import com.octagram.pollet.board.presentation.dto.response.PostFindAllResponse;
import com.octagram.pollet.global.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final PostRepository postRepository;
	private final BoardMapper boardMapper;
	private final PostMapper postMapper;

	@Transactional
	public BoardCreateResponse createBoard(BoardCreateRequest request) {
		Board board = boardMapper.toEntityFromCreateRequest(request);
		Board result = boardRepository.save(board);
		return boardMapper.toCreateResponse(result);
	}

	@Transactional
	public PostCreateResponse createPost(Long boardId, PostCreateRequest request) {
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new BusinessException(BoardErrorCode.BOARD_NOT_FOUND));
		Post post = postMapper.toEntity(request);
		post.setBoard(board);
		Post result = postRepository.save(post);
		return postMapper.toCreateResponse(result);
	}

	@Transactional
	public List<BoardFindAllResponse> findAllBoards() {
		List<Board> boards = boardRepository.findAll();
		return boards.stream()
			.map(boardMapper::toFindAllResponse)
			.toList();
	}

	@Transactional
	public List<PostFindAllResponse> findAllPosts() {
		List<Post> posts = postRepository.findAllQueryDsl();
		return posts.stream()
			.map(postMapper::toFindAllResponse)
			.toList();
	}
}
