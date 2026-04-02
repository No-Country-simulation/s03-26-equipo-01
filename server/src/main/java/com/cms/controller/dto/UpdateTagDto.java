package com.cms.controller.dto;

import com.cms.model.Tag;
import jakarta.validation.constraints.Pattern;

public record UpdateTagDto(
        @Pattern(regexp = ".*\\S.*") String name
) {
    public Tag toModel() {
        return Tag.builder()
                .name(name)
                .build();
    }
}
