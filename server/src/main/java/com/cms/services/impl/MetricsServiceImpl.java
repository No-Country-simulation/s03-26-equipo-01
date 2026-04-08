package com.cms.services.impl;

import com.cms.controller.dto.metrics.CategoryMetricDTO;
import com.cms.controller.dto.metrics.TagMetricDTO;
import com.cms.persistence.repository.MetricsRepository;
import com.cms.services.MetricsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService {

    private final MetricsRepository metricsRepository;

    @Override
    public List<TagMetricDTO> findAllMetricsTags(Long adminId) {
        return metricsRepository.findAllMetricsTags(adminId);
    }

    @Override
    public List<CategoryMetricDTO> findAllMetricsCategories(Long adminId) {
        return metricsRepository.findAllMetricsCategories(adminId);
    }
}
