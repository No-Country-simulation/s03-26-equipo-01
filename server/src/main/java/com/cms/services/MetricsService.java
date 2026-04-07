package com.cms.services;

import com.cms.controller.dto.metrics.CategoryMetricDTO;
import com.cms.controller.dto.metrics.MetricsResponseDTO;
import com.cms.controller.dto.metrics.TagMetricDTO;

public interface MetricsService {

    TagMetricDTO getTagMetrics(Long tagId);

    CategoryMetricDTO getCategoryMetrics(Long categoryId);

    MetricsResponseDTO getTestimonialsMetrics(Long tagId, Long categoryId);
}
