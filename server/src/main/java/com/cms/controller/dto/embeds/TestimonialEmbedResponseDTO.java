package com.cms.controller.dto.embeds;

import com.cms.controller.dto.tag.TagResponseDto;
import com.cms.model.testimonial.Media;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import com.cms.services.EmbedService;

import java.util.Set;
import java.util.stream.Collectors;

public record TestimonialEmbedResponseDTO(
        Long id,
//      String username
        String testimonial,
        int rating,
        String email,
        Media media,
        Set<String> tagsNames

) {
public static TestimonialEmbedResponseDTO fromModel(Testimonial testimonial, Set<String> tagsNames){
    return new TestimonialEmbedResponseDTO(
            testimonial.getId(),
//          testimonial.getUsername(),
            testimonial.getTestimonial(),
            testimonial.getRating(),
            testimonial.getEmail(),
            testimonial.getMedia(),
            tagsNames
    );
}
}
