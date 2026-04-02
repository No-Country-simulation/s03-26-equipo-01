package com.cms.controller.dto;

import java.time.LocalDateTime;

public record TagResponseDto(
        Long id,
        String name,
        String slug,
        boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
