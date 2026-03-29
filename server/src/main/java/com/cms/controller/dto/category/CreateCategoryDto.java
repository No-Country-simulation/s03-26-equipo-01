package com.cms.controller.dto.category;

import com.cms.model.Category;
import jakarta.validation.constraints.NotBlank;

public record CreateCategoryDto(
        @NotBlank String name,
        @NotBlank String slug,
        String description
) {
    public Category aModelo() {
        return Category.builder()
                .name(this.name())
                .slug(this.slug())
                .description(this.description())
                .build();
    }
}