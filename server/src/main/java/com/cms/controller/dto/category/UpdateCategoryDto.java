package com.cms.controller.dto.category;

import com.cms.model.testimonial.Category;

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