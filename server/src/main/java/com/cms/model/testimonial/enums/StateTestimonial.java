package com.cms.model.testimonial.enums;

import lombok.Getter;

@Getter
public enum StateTestimonial {
    DRAFT("Borrador"),
    PUBLISHED("Publicado"),
    ARCHIVED("Archivado");

    private final String label;

    StateTestimonial(String label) {
        this.label = label;
    }
}
