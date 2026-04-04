package com.cms.controller.dto.category;

import com.cms.model.Category;

public record CategoryResponseSimpleDTO(
        Long id,
        String name,
        String slug) {
    public static CategoryResponseSimpleDTO fromModel(Category category) {
        return new CategoryResponseSimpleDTO(
                category.getId(),
                category.getName(),
                category.getSlug()
        );
    }
}
