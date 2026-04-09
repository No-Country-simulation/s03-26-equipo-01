package com.cms.services.impl;

import com.cms.controller.dto.metrics.CategoryMetricDTO;
import com.cms.controller.dto.metrics.TagMetricDTO;
import com.cms.exception.EntityNotFoundException;
import com.cms.model.testimonial.Category;
import com.cms.persistence.repository.MetricsRepository;
import com.cms.persistence.sql.CategorySQLDAO;
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
    private final CategorySQLDAO categorySQLDAO;

    @Override
    public List<TagMetricDTO> findAllMetricsTags(Long adminId) {
        return metricsRepository.findAllMetricsTags(adminId);
    }

    @Override
    public List<CategoryMetricDTO> findAllMetricsCategories(Long adminId) {
        return metricsRepository.findAllMetricsCategories(adminId);
    }

    @Override
    public CategoryMetricDTO getCategoryMetrics(Long categoryId, Long adminId) {
        Category category = categorySQLDAO.findByIdAndAdminId(categoryId, adminId)
                .orElseThrow(() -> new EntityNotFoundException(Category.class.getSimpleName(), categoryId));

        return CategoryMetricDTO.fromModel(category, metricsRepository.countTestimonialsByCategoryId(categoryId));
    }
}
