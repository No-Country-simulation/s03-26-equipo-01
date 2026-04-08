package com.cms.services;

import com.cms.controller.dto.metrics.CategoryMetricDTO;
import com.cms.controller.dto.metrics.TagMetricDTO;
import java.util.List;

public interface MetricsService {

    List<TagMetricDTO> findAllMetricsTags(Long adminId);

    List<CategoryMetricDTO> findAllMetricsCategories(Long adminId);
}
