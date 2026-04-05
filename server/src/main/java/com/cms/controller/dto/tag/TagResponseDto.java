package com.cms.controller.dto.tag;

import com.cms.model.Tag;
import java.time.LocalDateTime;

public record TagResponseDto(
        Long id,
        String name,
        String slug,
        boolean isActive,
        LocalDateTime createdAt
) {

    public static TagResponseDto fromEntity(Tag tag) {
        return new TagResponseDto(
                tag.getId(),
                tag.getName(),
                tag.getSlug(),
                tag.isActive(),
                tag.getCreatedAt()
        );
    }
}