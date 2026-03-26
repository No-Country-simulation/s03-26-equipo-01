package com.cms.controller.dtos;

public record UpdateCategoryDto(
        String name,
        String slug,
        String description
) {
}
