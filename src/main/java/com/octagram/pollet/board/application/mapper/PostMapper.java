package com.octagram.pollet.board.application.mapper;

import org.mapstruct.Mapper;

import com.octagram.pollet.board.domain.model.Post;
import com.octagram.pollet.board.presentation.dto.request.PostCreateRequest;
import com.octagram.pollet.board.presentation.dto.response.PostCreateResponse;
import com.octagram.pollet.board.presentation.dto.response.PostFindAllResponse;

@Mapper(uses = {BoardMapper.class})
public interface PostMapper {

	/*
	* Entity -> DTO 변환 시 연관 관계를 가진 Board 가 포함되는 특수한 상황
	* Post 타입을 BoardInPostFindAllResponse DTO가 포함된 PostFindAllResponse 타입으로 변환하려고 합니다.
	* 이때 Board 타입을 BoardInPostFindAllResponse타입으로 변환하는것을 BoardMapper에 위임하게 됩니다.
	*
	* @Mapper(uses = {BoardMapper.class})
	* 어노테이션을 달면 위임할 수 있습니다.
	*
	* 만약 이런 필드가 여러개일경우,
	* @Mapper(uses = {BoardMapper.class, CommentMapper.class})
	* 처럼 여러 매퍼를 연결할 수 있습니다.
	* */

	// DTO -> Entity
	Post toEntity(PostCreateRequest request);

	// Entity -> DTO
	PostCreateResponse toCreateResponse(Post post);
	PostFindAllResponse toFindAllResponse(Post post);
}
