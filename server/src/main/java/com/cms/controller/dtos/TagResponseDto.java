package com.cms.controller.dtos;

import java.time.LocalDateTime;

public record TagResponseDto(
        Long id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
