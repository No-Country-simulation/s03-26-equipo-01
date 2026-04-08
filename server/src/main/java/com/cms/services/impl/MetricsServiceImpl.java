package com.cms.services.impl;

import com.cms.controller.dto.metrics.CategoryMetricDTO;
import com.cms.controller.dto.metrics.MetricsResponseDTO;
import com.cms.controller.dto.metrics.TagMetricDTO;
import com.cms.persistence.repository.MetricsRepository;
import com.cms.services.CategoryService;
import com.cms.services.MetricsService;
import com.cms.services.TagService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService {

    private final MetricsRepository metricsRepository;
    private final TagService tagService;
    private final CategoryService categoryService;

    @Override
    public List<TagMetricDTO> findAllMetricsTags() {
        return metricsRepository.findAllMetricsTags();
    }

    @Override
    public CategoryMetricDTO getCategoryMetrics(Long categoryId) {
        return CategoryMetricDTO.fromModel(
                categoryService.findById(categoryId),
                metricsRepository.countTestimonialsByCategoryId(categoryId)
        );
    }

    @Override
    public MetricsResponseDTO getTestimonialsMetrics(Long tagId, Long categoryId) {
        return new MetricsResponseDTO(
                findTagMetric(tagId),
                getCategoryMetrics(categoryId)
        );
    }

    private TagMetricDTO findTagMetric(Long tagId) {
        return findAllMetricsTags().stream()
                .filter(metric -> metric.id().equals(tagId))
                .findFirst()
                .orElseGet(() -> TagMetricDTO.fromModel(tagService.findById(tagId), 0L));
    }
}
