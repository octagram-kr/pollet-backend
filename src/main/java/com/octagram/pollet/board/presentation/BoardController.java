package com.octagram.pollet.board.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.octagram.pollet.board.application.BoardService;
import com.octagram.pollet.board.presentation.dto.request.BoardCreateRequest;
import com.octagram.pollet.board.presentation.dto.request.PostCreateRequest;
import com.octagram.pollet.board.presentation.dto.response.BoardCreateResponse;
import com.octagram.pollet.board.presentation.dto.response.BoardFindAllResponse;
import com.octagram.pollet.board.presentation.dto.response.PostCreateResponse;
import com.octagram.pollet.board.presentation.dto.response.PostFindAllResponse;
import com.octagram.pollet.global.dto.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@GetMapping
	public ApiResponse<List<BoardFindAllResponse>> findAllBoards() {
		List<BoardFindAllResponse> result = boardService.findAllBoards();
		return ApiResponse.success(result);
	}

	@GetMapping("/{boardId}")
	public ApiResponse<List<PostFindAllResponse>> findAllPosts() {
		List<PostFindAllResponse> result = boardService.findAllPosts();
		return ApiResponse.success(result);
	}

	@PostMapping
	public ApiResponse<BoardCreateResponse> createBoard(@RequestBody @Valid BoardCreateRequest request) {
		BoardCreateResponse result = boardService.createBoard(request);
		return ApiResponse.success(result);
	}

	@PostMapping("/{boardId}")
	public ApiResponse<PostCreateResponse> addPost(@PathVariable Long boardId, @RequestBody @Valid PostCreateRequest request) {
		PostCreateResponse result = boardService.createPost(boardId, request);
		return ApiResponse.success(result);
	}
}
