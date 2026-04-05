package com.cms.controller.dto.embeds;

import com.cms.model.testimonial.Testimonial;

public record TestimonialEmbedResponseDTO(
        Long id,
        String testimonial,
        int rating,
        String email



) {
public static TestimonialEmbedResponseDTO fromModel(Testimonial testimonial){
    return new TestimonialEmbedResponseDTO(
            testimonial.getId(),
            testimonial.getTestimonial(),
            testimonial.getRating(),
            testimonial.getEmail()

    );
}
}
