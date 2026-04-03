package com.cms.controller.dto;

import com.cms.model.Tag;
import java.time.LocalDateTime;

public record TagResponseDto(
        Long id,
        String name,
        String slug,
        boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static TagResponseDto fromEntity(Tag tag) {
        return new TagResponseDto(
                tag.getId(),
                tag.getName(),
                tag.getSlug(),
                tag.isActive(),
                tag.getCreatedAt(),
                tag.getUpdatedAt()
        );
    }
}