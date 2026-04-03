package com.cms.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record TagRequestDTO(
        @NotBlank String name
) {}