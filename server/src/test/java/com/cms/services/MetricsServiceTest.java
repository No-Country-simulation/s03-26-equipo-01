package com.cms.services;

import com.cms.controller.dto.metrics.CategoryMetricDTO;
import com.cms.controller.dto.metrics.TagMetricDTO;
import com.cms.persistence.repository.MetricsRepository;
import com.cms.services.impl.MetricsServiceImpl;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MetricsServiceTest {

    @InjectMocks
    private MetricsServiceImpl metricsService;

    @Mock
    private MetricsRepository metricsRepository;

    private Long adminId;
    private Long tagId;
    private Long categoryId;
    private Long tagWithoutTestimonialsId;
    private List<TagMetricDTO> tagMetrics;
    private List<CategoryMetricDTO> categoryMetrics;

    @BeforeEach
    void setUp() {
        adminId = 1L;
        tagId = 10L;
        categoryId = 20L;
        tagWithoutTestimonialsId = 30L;
        tagMetrics = List.of(
                new TagMetricDTO(tagId, "primary tag", "primary-tag", 2L),
                new TagMetricDTO(11L, "secondary tag", "secondary-tag", 2L),
                new TagMetricDTO(tagWithoutTestimonialsId, "tag without testimonials", "tag-without-testimonials", 0L)
        );
        categoryMetrics = List.of(
                new CategoryMetricDTO(categoryId, "Primary Category", "primary-category", 2L),
                new CategoryMetricDTO(21L, "Secondary Category", "secondary-category", 1L),
                new CategoryMetricDTO(22L, "Category Without Testimonials", "category-without-testimonials", 0L)
        );
    }

    @Test
    void findAllMetricsTagsShouldReturnTestimonialsCountForEveryTag() {
        when(metricsRepository.findAllMetricsTags(adminId)).thenReturn(tagMetrics);

        List<TagMetricDTO> metrics = metricsService.findAllMetricsTags(adminId);
        TagMetricDTO primaryTagMetrics = metrics.stream()
                .filter(metric -> metric.id().equals(tagId))
                .findFirst()
                .orElseThrow();
        TagMetricDTO secondaryTagMetrics = metrics.stream()
                .filter(metric -> metric.slug().equals("secondary-tag"))
                .findFirst()
                .orElseThrow();
        TagMetricDTO tagWithoutTestimonialsMetrics = metrics.stream()
                .filter(metric -> metric.id().equals(tagWithoutTestimonialsId))
                .findFirst()
                .orElseThrow();

        assertEquals(3, metrics.size());
        assertEquals(tagId, primaryTagMetrics.id());
        assertEquals("primary tag", primaryTagMetrics.name());
        assertEquals("primary-tag", primaryTagMetrics.slug());
        assertEquals(2L, primaryTagMetrics.testimonialsCount());
        assertEquals(2L, secondaryTagMetrics.testimonialsCount());
        assertEquals(0L, tagWithoutTestimonialsMetrics.testimonialsCount());
        assertTrue(metrics.stream().noneMatch(metric -> metric.slug().equals("foreign-tag")));
    }

    @Test
    void findAllMetricsCategoriesShouldReturnTestimonialsCountForAdminCategories() {
        when(metricsRepository.findAllMetricsCategories(adminId)).thenReturn(categoryMetrics);

        List<CategoryMetricDTO> metrics = metricsService.findAllMetricsCategories(adminId);
        CategoryMetricDTO primaryCategoryMetrics = metrics.stream()
                .filter(metric -> metric.id().equals(categoryId))
                .findFirst()
                .orElseThrow();
        CategoryMetricDTO secondaryCategoryMetrics = metrics.stream()
                .filter(metric -> metric.slug().equals("secondary-category"))
                .findFirst()
                .orElseThrow();
        CategoryMetricDTO categoryWithoutTestimonialsMetrics = metrics.stream()
                .filter(metric -> metric.slug().equals("category-without-testimonials"))
                .findFirst()
                .orElseThrow();

        assertEquals(3, metrics.size());
        assertEquals(categoryId, primaryCategoryMetrics.id());
        assertEquals("Primary Category", primaryCategoryMetrics.name());
        assertEquals("primary-category", primaryCategoryMetrics.slug());
        assertEquals(2L, primaryCategoryMetrics.testimonialsCount());
        assertEquals(1L, secondaryCategoryMetrics.testimonialsCount());
        assertEquals(0L, categoryWithoutTestimonialsMetrics.testimonialsCount());
        assertTrue(metrics.stream().noneMatch(metric -> metric.slug().equals("foreign-category")));
    }

    @Test
    void findAllMetricsCategoriesShouldReturnEmptyListWhenAdminDoesNotExist() {
        when(metricsRepository.findAllMetricsCategories(9999L)).thenReturn(List.of());

        List<CategoryMetricDTO> metrics = metricsService.findAllMetricsCategories(9999L);

        assertTrue(metrics.isEmpty());
    }
}
