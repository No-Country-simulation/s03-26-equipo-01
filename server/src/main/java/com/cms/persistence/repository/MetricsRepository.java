package com.cms.persistence.repository;

public interface MetricsRepository {

    long countTestimonialsByTagId(Long tagId);

    long countTestimonialsByCategoryId(Long categoryId);
}
