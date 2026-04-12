package com.cms.controller.dto.category;

import com.cms.model.testimonial.Category;
import java.time.LocalDateTime;

public record CategoryResponseDto(
        Long id,
        String name,
        String slug,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CategoryResponseDto fromModel(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getSlug(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }
}