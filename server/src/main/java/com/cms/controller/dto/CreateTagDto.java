package com.cms.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateTagDto(
        @NotBlank String name
) {}