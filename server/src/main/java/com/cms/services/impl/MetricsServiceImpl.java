package com.cms.services.impl;

import com.cms.controller.dto.metrics.CategoryMetricDTO;
import com.cms.controller.dto.metrics.MetricsResponseDTO;
import com.cms.controller.dto.metrics.TagMetricDTO;
import com.cms.persistence.repository.MetricsRepository;
import com.cms.services.CategoryService;
import com.cms.services.MetricsService;
import com.cms.services.TagService;
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
    public TagMetricDTO getTagMetrics(Long tagId) {
        return TagMetricDTO.fromModel(
                tagService.findById(tagId),
                metricsRepository.countTestimonialsByTagId(tagId)
        );
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
                getTagMetrics(tagId),
                getCategoryMetrics(categoryId)
        );
    }
}
