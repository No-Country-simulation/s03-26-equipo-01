package com.cms.controller.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryDto(
        @NotBlank String name,
        @NotBlank String slug,
        String description
) {
}
