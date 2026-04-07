package com.cms.controller.dto.metrics;

import com.cms.model.testimonial.Tag;

public record TagMetricDTO(
        Long id,
        String name,
        String slug,
        long testimonialsCount
) {

    public static TagMetricDTO fromModel(Tag tag, long testimonialsCount) {
        return new TagMetricDTO(
                tag.getId(),
                tag.getName(),
                tag.getSlug(),
                testimonialsCount
        );
    }
}
