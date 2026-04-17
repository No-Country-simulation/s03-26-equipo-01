package com.cms.controller.dto.category;

import com.cms.model.testimonial.Category;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public record CategoryResponseDto(
        Long id,
        String name,
        String slug,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDateTime createdAt,
        @JsonFormat(pattern = "dd/MM/yyyy")
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