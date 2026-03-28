package com.cms.controller.dtos;

import com.cms.model.Category;

public record UpdateCategoryDto(
        String name,
        String slug,
        String description
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