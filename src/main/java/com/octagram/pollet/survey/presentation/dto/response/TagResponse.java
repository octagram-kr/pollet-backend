package com.octagram.pollet.survey.presentation.dto.response;

import com.octagram.pollet.survey.domain.model.Tag;

public record TagResponse(
        Long id,
        String name
) {

    public static TagResponse from(Tag tag) {
        return new TagResponse(
                tag.getId(),
                tag.getName()
        );
    }
}
