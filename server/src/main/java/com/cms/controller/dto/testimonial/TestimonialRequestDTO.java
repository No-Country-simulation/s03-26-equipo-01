package com.cms.controller.dto.testimonial;

import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TestimonialRequestDTO(
        @NotNull
        @NotBlank
        String testimonial,

        @NotNull
        @NotBlank
        String email,

        @NotNull
        @Min(0)
        @Max(5)
        int rating,

        @NotNull
        @NotBlank
        Long idEmbed
) {
    public Testimonial toModel() {
        return Testimonial.builder()
                .testimonial(testimonial)
                .email(email)
                .rating(rating)
                .state(StateTestimonial.DRAFT)
                .createdAt(LocalDate.now())
                .build();
    }
}
