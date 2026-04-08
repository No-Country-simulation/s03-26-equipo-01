package com.cms.services.impl;

import com.cms.controller.dto.metrics.CategoryMetricDTO;
import com.cms.controller.dto.metrics.MetricsResponseDTO;
import com.cms.controller.dto.metrics.TagMetricDTO;
import com.cms.model.testimonial.Tag;
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
    public List<TagMetricDTO> findAllMetricsTags(Long adminId) {
        return metricsRepository.findAllMetricsTags(adminId);
    }

    @Override
    public List<CategoryMetricDTO> findAllMetricsCategories(Long adminId) {
        return metricsRepository.findAllMetricsCategories(adminId);
    }

    @Override
    public MetricsResponseDTO getTestimonialsMetrics(Long tagId, Long categoryId) {
        return new MetricsResponseDTO(
                findTagMetric(tagId),
                findCategoryMetric(categoryId)
        );
    }

    private TagMetricDTO findTagMetric(Long tagId) {
        Tag tag = tagService.findById(tagId);
        TagMetricDTO emptyMetric = TagMetricDTO.fromModel(tag, 0L);
        return findAllMetricsTags(tag.getCreator().getId()).stream()
                .filter(metric -> metric.id().equals(tagId))
                .findFirst()
                .orElse(emptyMetric);
    }

    private CategoryMetricDTO findCategoryMetric(Long categoryId) {
        return CategoryMetricDTO.fromModel(
                categoryService.findById(categoryId),
                metricsRepository.countTestimonialsByCategoryId(categoryId)
        );
    }
}
