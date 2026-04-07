package com.cms.persistence.repository.impl;

import com.cms.persistence.repository.MetricsRepository;
import com.cms.persistence.sql.TestimonialSQLDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MetricsRepositoryImpl implements MetricsRepository {

    private final TestimonialSQLDAO testimonialSQLDAO;

    @Override
    public long countTestimonialsByTagId(Long tagId) {
        return testimonialSQLDAO.countTestimonialsByTagId(tagId);
    }

    @Override
    public long countTestimonialsByCategoryId(Long categoryId) {
        return testimonialSQLDAO.countTestimonialsByCategoryId(categoryId);
    }
}
