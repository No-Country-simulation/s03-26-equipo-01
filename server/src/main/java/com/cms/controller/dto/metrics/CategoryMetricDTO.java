package com.cms.controller.dto.metrics;

import com.cms.model.testimonial.Category;

public record CategoryMetricDTO(
        Long id,
        String name,
        String slug,
        long testimonialsCount
) {

    public static CategoryMetricDTO fromModel(Category category, long testimonialsCount) {
        return new CategoryMetricDTO(
                category.getId(),
                category.getName(),
                category.getSlug(),
                testimonialsCount
        );
    }
}
