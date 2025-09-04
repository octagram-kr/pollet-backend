package com.octagram.pollet.board.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.octagram.pollet.board.domain.model.Board;
import com.octagram.pollet.board.presentation.dto.request.BoardCreateRequest;
import com.octagram.pollet.board.presentation.dto.response.BoardCreateResponse;
import com.octagram.pollet.board.presentation.dto.response.BoardFindAllResponse;
import com.octagram.pollet.board.presentation.dto.response.BoardInPostFindAllResponse;

@Mapper
public interface BoardMapper {

	/*
	* [만약 필드명이 다를 때]
	* DTO의 'boardId' 필드를 엔티티의 'id' 필드에 매핑한다고 가정했을 때,
	* @Mapping(source = "boardId", target = "id")
	* 어노테이션을 메서드 위에 달아주면 됩니다.
	*
	* source, target 작성 시 자동완성을 지원합니다.
	* MacOS 기본설정 기준 [ctrl + 스페이스]를 누르면 자동완성 후보 목록이 나옵니다.
	* */

	// DTO -> Entity
	Board toEntityFromCreateRequest(BoardCreateRequest request);

	// Entity -> DTO
	BoardCreateResponse toCreateResponse(Board board);
	BoardFindAllResponse toFindAllResponse(Board board);
	BoardInPostFindAllResponse toPostFindAllResponse(Board board);
}
