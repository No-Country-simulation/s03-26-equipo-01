package com.cms.persistence.repository;

import com.cms.controller.dto.metrics.TagMetricDTO;
import java.util.List;

public interface MetricsRepository {

    List<TagMetricDTO> findAllMetricsTags();

    long countTestimonialsByCategoryId(Long categoryId);
}
