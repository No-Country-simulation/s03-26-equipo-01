package com.cms.controller.dto;

import com.cms.model.Tag;
import jakarta.validation.constraints.NotBlank;

public record TagRequestDTO(
        @NotBlank String name
) {
    public Tag toEntity() {
        return Tag.builder()
                .name(this.name)
                .build();
    }
}