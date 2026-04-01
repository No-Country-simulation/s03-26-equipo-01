package com.cms.controller.dto.tag;

import com.cms.model.Tag;
import jakarta.validation.constraints.Pattern;

public record UpdateTagDto(
        @Pattern(regexp = ".*\\S.*") String name
) {
    public void aplicar(Tag tag) {
        if (name != null) {
            tag.setName(name);
        }
    }
}
