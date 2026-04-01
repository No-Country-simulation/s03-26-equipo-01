package com.cms.controller.dto.tag;

import com.cms.model.Tag;
import jakarta.validation.constraints.NotBlank;

public record CreateTagDto(
        @NotBlank String name
) {
    public Tag aModelo() {
        return Tag.builder()
                .name(name)
                .build();
    }
}
