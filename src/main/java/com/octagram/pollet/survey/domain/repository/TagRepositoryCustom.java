package com.octagram.pollet.survey.domain.repository;

import java.util.List;

import com.octagram.pollet.survey.domain.model.Tag;

public interface TagRepositoryCustom {

	List<Tag> findByMemberId(Long memberId);
}
