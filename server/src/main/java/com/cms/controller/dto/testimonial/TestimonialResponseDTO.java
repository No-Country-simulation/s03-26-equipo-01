package com.cms.controller.dto.testimonial;

import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;


import java.time.LocalDate;

public record TestimonialResponseDTO(
         Long id,
         String testimonial,
         long idEmbed,
         int rating,
         String email,
         StateTestimonial state,
         LocalDate createdAt
) {
    public static TestimonialResponseDTO fromModel(Testimonial testimonial) {
        return new TestimonialResponseDTO(
                testimonial.getId(),
                testimonial.getTestimonial(),
                testimonial.getEmbed().getId(),
                testimonial.getRating(),
                testimonial.getEmail(),
                testimonial.getState(),
                testimonial.getCreatedAt()
        );
    }
}
