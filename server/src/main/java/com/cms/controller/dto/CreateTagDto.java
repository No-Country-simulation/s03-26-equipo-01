package com.cms.controller.dto;

import com.cms.model.Tag;
import jakarta.validation.constraints.NotBlank;

public record CreateTagDto(
        @NotBlank String name
) {
    public Tag toModel() {
        return Tag.builder()
                .name(name)
                .build();
    }
}
