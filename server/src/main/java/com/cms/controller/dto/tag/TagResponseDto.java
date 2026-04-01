package com.cms.controller.dto.tag;

import com.cms.model.Tag;

public record TagResponseDto(
        Long id,
        String name,
        String slug
) {
    public static TagResponseDto fromModel(Tag tag) {
        return new TagResponseDto(
                tag.getId(),
                tag.getName(),
                tag.getSlug()
        );
    }
}
