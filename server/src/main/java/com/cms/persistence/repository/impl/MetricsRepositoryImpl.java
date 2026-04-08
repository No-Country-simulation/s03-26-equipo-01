package com.cms.persistence.repository.impl;

import com.cms.controller.dto.metrics.TagMetricDTO;
import com.cms.persistence.repository.MetricsRepository;
import com.cms.persistence.sql.TestimonialSQLDAO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MetricsRepositoryImpl implements MetricsRepository {

    private final TestimonialSQLDAO testimonialSQLDAO;

    @Override
    public List<TagMetricDTO> findAllMetricsTags() {
        return testimonialSQLDAO.findAllMetricsTags();
    }

    @Override
    public long countTestimonialsByCategoryId(Long categoryId) {
        return testimonialSQLDAO.countTestimonialsByCategoryId(categoryId);
    }
}
