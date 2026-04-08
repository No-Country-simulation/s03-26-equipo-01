package com.cms.services;

import com.cms.controller.dto.metrics.CategoryMetricDTO;
import com.cms.controller.dto.metrics.MetricsResponseDTO;
import com.cms.controller.dto.metrics.TagMetricDTO;
import com.cms.exception.EntityNotFoundException;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Category;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.admin.Admin;
import com.cms.persistence.sql.TestimonialSQLDAO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
class MetricsServiceTest {

    private final MetricsService metricsService;
    private final UserService userService;
    private final EmbedService embedService;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final TestimonialSQLDAO testimonialSQLDAO;

    private Long adminId;
    private Long tagId;
    private Long categoryId;
    private Long tagWithoutTestimonialsId;

    @BeforeEach
    void setUp() {
        Admin admin = (Admin) userService.save(Admin.builder()
                .email("metrics-admin@test.com")
                .password("123")
                .firstName("Metrics")
                .lastName("Admin")
                .build());
        adminId = admin.getId();

        Admin secondAdmin = (Admin) userService.save(Admin.builder()
                .email("metrics-admin-2@test.com")
                .password("123")
                .firstName("Metrics")
                .lastName("Admin Two")
                .build());

        Embed embed = embedService.registerEmbed(admin.getId(), new Embed());
        Embed secondEmbed = embedService.registerEmbed(secondAdmin.getId(), new Embed());

        Category primaryCategory = categoryService.create(
                Category.builder()
                        .name("Primary Category")
                        .slug("primary-category")
                        .description("Primary category for metrics")
                        .build(),
                admin.getId()
        );

        Category secondaryCategory = categoryService.create(
                Category.builder()
                        .name("Secondary Category")
                        .slug("secondary-category")
                        .description("Secondary category for metrics")
                        .build(),
                admin.getId()
        );

        Category categoryWithoutTestimonials = categoryService.create(
                Category.builder()
                        .name("Category Without Testimonials")
                        .slug("category-without-testimonials")
                        .description("Category without testimonials for metrics")
                        .build(),
                admin.getId()
        );

        Category foreignCategory = categoryService.create(
                Category.builder()
                        .name("Foreign Category")
                        .slug("foreign-category")
                        .description("Category from another admin")
                        .build(),
                secondAdmin.getId()
        );

        Tag primaryTag = tagService.create(Tag.builder().name("Primary Tag").build(), admin.getId());
        Tag secondaryTag = tagService.create(Tag.builder().name("Secondary Tag").build(), admin.getId());
        Tag tagWithoutTestimonials = tagService.create(Tag.builder().name("Tag Without Testimonials").build(), admin.getId());

        tagId = primaryTag.getId();
        categoryId = primaryCategory.getId();
        tagWithoutTestimonialsId = tagWithoutTestimonials.getId();

        saveTestimonial(embed, primaryCategory, List.of(primaryTag));
        saveTestimonial(embed, primaryCategory, List.of(primaryTag, secondaryTag));
        saveTestimonial(embed, secondaryCategory, List.of(secondaryTag));
        saveTestimonial(secondEmbed, foreignCategory, List.of());
    }

    @Test
    void findAllMetricsTagsShouldReturnTestimonialsCountForEveryTag() {
        List<TagMetricDTO> metrics = metricsService.findAllMetricsTags();
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
    }

    @Test
    void findAllMetricsCategoriesShouldReturnTestimonialsCountForAdminCategories() {
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
        List<CategoryMetricDTO> metrics = metricsService.findAllMetricsCategories(9999L);

        assertTrue(metrics.isEmpty());
    }

    @Test
    void getTestimonialsMetricsShouldReturnBothCountersInSingleResponse() {
        MetricsResponseDTO metrics = metricsService.getTestimonialsMetrics(tagId, categoryId);

        assertEquals(2L, metrics.tag().testimonialsCount());
        assertEquals(2L, metrics.category().testimonialsCount());
    }

    @Test
    void getTestimonialsMetricsShouldFailWhenTagDoesNotExist() {
        assertThrows(EntityNotFoundException.class, () -> metricsService.getTestimonialsMetrics(9999L, categoryId));
    }

    private void saveTestimonial(Embed embed, Category category, List<Tag> tags) {
        testimonialSQLDAO.saveAndFlush(Testimonial.builder()
                .testimonial("Metric testimonial for " + category.getSlug())
                .rating(5)
                .email(category.getSlug() + "@test.com")
                .state(StateTestimonial.DRAFT)
                .embed(embed)
                .category(category)
                .tags(tags)
                .build());
    }
}
