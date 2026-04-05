package com.cms.controller.dto.tag;

import com.cms.model.testimonial.Tag;
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