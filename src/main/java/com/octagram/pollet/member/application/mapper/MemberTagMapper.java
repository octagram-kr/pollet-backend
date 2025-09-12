package com.octagram.pollet.member.application.mapper;

import org.mapstruct.Mapper;

import com.octagram.pollet.member.domain.model.MemberTag;
import com.octagram.pollet.member.presentation.dto.response.MemberTagResponse;
import com.octagram.pollet.survey.application.mapper.TagMapper;

@Mapper(uses = {TagMapper.class})
public interface MemberTagMapper {

	MemberTagResponse toResponse(MemberTag memberTag);
}
