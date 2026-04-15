package com.cms.controller.dto.tag;

import jakarta.validation.constraints.NotNull;

public record TagRequestSearchDTO(
        @NotNull
        String name
) {
}
