package com.cms.controller.dto.category;

import com.cms.model.testimonial.Category;
import jakarta.validation.constraints.NotBlank;


public record CreateCategoryDto(
        @NotBlank String name,
        String description
) {
    public Category aModelo() {
        return Category.builder()
                .name(this.name())
                .description(this.description())
                .build();
    }
}