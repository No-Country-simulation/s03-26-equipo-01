package com.cms.controller.dtos;

import jakarta.validation.constraints.NotBlank;

public record TagRequestDto(
        @NotBlank String name
) {
}
