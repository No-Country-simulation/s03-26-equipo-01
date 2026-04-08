package com.cms.persistence.repository;

import com.cms.controller.dto.metrics.CategoryMetricDTO;
import com.cms.controller.dto.metrics.TagMetricDTO;
import java.util.List;

public interface MetricsRepository {

    List<TagMetricDTO> findAllMetricsTags();

    List<CategoryMetricDTO> findAllMetricsCategories(Long adminId);

    long countTestimonialsByCategoryId(Long categoryId);
}
