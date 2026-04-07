package com.cms.model.testimonial.enums;

import lombok.Getter;

@Getter
public enum StateTestimonial {
    APPROVED("Aprobado"),
    DRAFT("Borrador"),
    PUBLISHED("Publicado"),
    PENDING("Pendiente"),;

    private final String label;

    StateTestimonial(String label) {
        this.label = label;
    }
}
