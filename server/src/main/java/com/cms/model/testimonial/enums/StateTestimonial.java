package com.cms.model.testimonial.enums;

import com.cms.model.testimonial.state.TestimonialState;
import com.cms.model.testimonial.state.impl.ApprovedState;
import com.cms.model.testimonial.state.impl.DraftState;
import com.cms.model.testimonial.state.impl.PendingState;
import com.cms.model.testimonial.state.impl.PublishedState;
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

    public static StateTestimonial fromState(TestimonialState testimonialState) {
        return switch (testimonialState) {
            case DraftState s -> DRAFT;
            case PendingState s -> PENDING;
            case ApprovedState s -> APPROVED;
            case PublishedState s -> PUBLISHED;
            default -> throw new IllegalArgumentException("Estado desconocido: " + testimonialState.getClass());
        };
    }

    public TestimonialState toState() {
        return switch (this) {
            case DRAFT     -> new DraftState();
            case PENDING   -> new PendingState();
            case APPROVED  -> new ApprovedState();
            case PUBLISHED -> new PublishedState();
        };
    }
}
