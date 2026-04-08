package com.cms.services;

import com.cms.controller.dto.metrics.CategoryMetricDTO;
import com.cms.controller.dto.metrics.MetricsResponseDTO;
import com.cms.controller.dto.metrics.TagMetricDTO;
import com.cms.exception.EntityNotFoundException;
import com.cms.model.testimonial.Category;
import com.cms.model.testimonial.Tag;
import com.cms.model.user.impl.admin.Admin;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MetricsServiceTest {

    @InjectMocks
    private MetricsServiceImpl metricsService;

    @Mock
    private MetricsRepository metricsRepository;

    @Mock
    private TagService tagService;

    @Mock
    private CategoryService categoryService;

    private Long adminId;
    private Long tagId;
    private Long categoryId;
    private Long tagWithoutTestimonialsId;
    private List<TagMetricDTO> tagMetrics;
    private List<CategoryMetricDTO> categoryMetrics;
    private Tag primaryTag;
    private Category primaryCategory;

    @BeforeEach
    void setUp() {
        adminId = 1L;
        tagId = 10L;
        categoryId = 20L;
        tagWithoutTestimonialsId = 30L;
        primaryTag = Tag.builder()
                .id(tagId)
                .name("primary tag")
                .slug("primary-tag")
                .creator(Admin.builder().id(adminId).build())
                .build();
        primaryCategory = Category.builder()
                .id(categoryId)
                .name("Primary Category")
                .slug("primary-category")
                .build();
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

    @Test
    void getTestimonialsMetricsShouldReturnBothCountersInSingleResponse() {
        when(tagService.findById(tagId)).thenReturn(primaryTag);
        when(metricsRepository.findAllMetricsTags(adminId)).thenReturn(tagMetrics);
        when(categoryService.findById(categoryId)).thenReturn(primaryCategory);
        when(metricsRepository.countTestimonialsByCategoryId(categoryId)).thenReturn(2L);

        MetricsResponseDTO metrics = metricsService.getTestimonialsMetrics(tagId, categoryId);

        assertEquals(2L, metrics.tag().testimonialsCount());
        assertEquals(2L, metrics.category().testimonialsCount());
    }

    @Test
    void getTestimonialsMetricsShouldFailWhenTagDoesNotExist() {
        when(tagService.findById(9999L)).thenThrow(new EntityNotFoundException("tag", 9999L));

        assertThrows(EntityNotFoundException.class, () -> metricsService.getTestimonialsMetrics(9999L, categoryId));
    }
}
