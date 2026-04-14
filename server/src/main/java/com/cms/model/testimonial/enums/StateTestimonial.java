package com.cms.model.testimonial.enums;

import com.cms.model.testimonial.state.TestimonialState;
import com.cms.model.testimonial.state.impl.*;
import lombok.Getter;

@Getter
public enum StateTestimonial {
    APPROVED("Aprobado"),
    DRAFT("Borrador"),
    PUBLISHED("Publicado"),
    PENDING("Pendiente"),
    ARCHIVED("Archivado")
    ;

    private final String label;

    StateTestimonial(String label) {
        this.label = label;
    }

    public static StateTestimonial fromState(TestimonialState testimonialState) {
        return switch (testimonialState) {
            case DraftState s -> DRAFT;
            case PendingState s -> PENDING;
            case ApprovedState s -> APPROVED;
            case PublishedState s -> PUBLISHED;
            case ArchivedState s -> ARCHIVED;
            default -> throw new IllegalArgumentException("Estado desconocido: " + testimonialState.getClass());
        };
    }

    public TestimonialState toState() {
        return switch (this) {
            case DRAFT     -> new DraftState();
            case PENDING   -> new PendingState();
            case APPROVED  -> new ApprovedState();
            case PUBLISHED -> new PublishedState();
            case ARCHIVED -> new ArchivedState();
        };
    }
}
