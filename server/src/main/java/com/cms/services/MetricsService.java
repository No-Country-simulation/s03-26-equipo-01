package com.cms.services;

import com.cms.controller.dto.metrics.CategoryMetricDTO;
import com.cms.controller.dto.metrics.MetricsResponseDTO;
import com.cms.controller.dto.metrics.TagMetricDTO;
import java.util.List;

public interface MetricsService {

    List<TagMetricDTO> findAllMetricsTags();

    CategoryMetricDTO getCategoryMetrics(Long categoryId);

    MetricsResponseDTO getTestimonialsMetrics(Long tagId, Long categoryId);
}
