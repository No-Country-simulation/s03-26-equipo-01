package com.cms.controller.dtos;

import com.cms.model.Category;
import jakarta.validation.constraints.Size;

public record UpdateCategoryDto(
        @Size(min = 3, max = 50)
        String name,

        @Size(max = 255)
        String description,

        String slug
) {
        public void aplicar(Category category) {
                if (this.name() != null) {
                        category.setName(this.name());
                }
                if (this.slug() != null) {
                        category.setSlug(this.slug());
                }
                if (this.description() != null) {
                        category.setDescription(this.description());
                }
        }
}