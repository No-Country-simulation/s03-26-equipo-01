package com.cms.controller.dtos;

import jakarta.validation.constraints.Size;

public record UpdateCategoryDto(
        @Size(min = 3, max = 50)
        String name,

        @Size(max = 255)
        String description,

        String slug
) {}
