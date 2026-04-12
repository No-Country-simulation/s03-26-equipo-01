package com.cms.services;

import com.cms.controller.dto.metrics.CategoryMetricDTO;
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
import org.junit.jupiter.api.AfterEach;
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
@Transactional
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class MetricsServiceTest {


    private final MetricsService metricsService;
    private final UserService userService;
    private final EmbedService embedService;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final TestimonialService testimonialService;
    private final ResetService resetService;

    private Long adminId;
    private Long tagId;
    private Long categoryId;
    private Long categoryWithoutTestimonialsId;
    private Long foreignCategoryId;
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

        Admin otherAdmin = (Admin) userService.save(Admin.builder()
                .email("metrics-admin-2@test.com")
                .password("123")
                .firstName("Metrics")
                .lastName("Admin Two")
                .build());

        Embed embed = embedService.registerEmbed(adminId, new Embed());
        Embed otherEmbed = embedService.registerEmbed(otherAdmin.getId(), new Embed());

        Category primaryCategory = categoryService.create(
                Category.builder()
                        .name("Primary Category")
                        .build(),
                adminId
        );

        Category secondaryCategory = categoryService.create(
                Category.builder()
                        .name("Secondary Category")
                        .build(),
                adminId
        );

        Category categoryWithoutTestimonials = categoryService.create(
                Category.builder()
                        .name("Category Without Testimonials")
                        .build(),
                adminId
        );

        Category foreignCategory = categoryService.create(
                Category.builder()
                        .name("Foreign Category")
                        .build(),
                otherAdmin.getId()
        );

        Tag primaryTag = tagService.create(Tag.builder().name("Primary Tag").build(), adminId);
        Tag secondaryTag = tagService.create(Tag.builder().name("Secondary Tag").build(), adminId);
        Tag tagWithoutTestimonials = tagService.create(Tag.builder().name("Tag Without Testimonials").build(), adminId);
        Tag foreignTag = tagService.create(Tag.builder().name("Foreign Tag").build(), otherAdmin.getId());

        tagId = primaryTag.getId();
        categoryId = primaryCategory.getId();
        categoryWithoutTestimonialsId = categoryWithoutTestimonials.getId();
        foreignCategoryId = foreignCategory.getId();
        tagWithoutTestimonialsId = tagWithoutTestimonials.getId();

        saveTestimonial(admin, primaryCategory, List.of(primaryTag.getId()));
        saveTestimonial(admin, primaryCategory, List.of(primaryTag.getId(), secondaryTag.getId()));
        saveTestimonial( admin, secondaryCategory, List.of(secondaryTag.getId()));
        saveTestimonial(otherAdmin, foreignCategory, List.of(foreignTag.getId()));
    }

    @Test
    void findAllMetricsTagsShouldReturnTestimonialsCountForEveryTag() {
        List<TagMetricDTO> metrics = metricsService.findAllMetricsTags(adminId);
        TagMetricDTO primaryTagMetrics = metrics.stream()
                .filter(metric -> metric.id().equals(tagId))
                .findFirst()
                .orElseThrow();
        TagMetricDTO tagWithoutTestimonialsMetrics = metrics.stream()
                .filter(metric -> metric.id().equals(tagWithoutTestimonialsId))
                .findFirst()
                .orElseThrow();

        assertEquals(3, metrics.size());
        assertEquals(tagId, primaryTagMetrics.id());
        assertEquals("primary tag", primaryTagMetrics.name());
        assertEquals(2L, primaryTagMetrics.testimonialsCount());
        assertEquals(0L, tagWithoutTestimonialsMetrics.testimonialsCount());
    }

    @Test
    void findAllMetricsCategoriesShouldReturnTestimonialsCountForAdminCategories() {
        List<CategoryMetricDTO> metrics = metricsService.findAllMetricsCategories(adminId);
        CategoryMetricDTO primaryCategoryMetrics = metrics.stream()
                .filter(metric -> metric.id().equals(categoryId))
                .findFirst()
                .orElseThrow();

        assertEquals(3, metrics.size());
        assertEquals(categoryId, primaryCategoryMetrics.id());
        assertEquals("Primary Category", primaryCategoryMetrics.name());
        assertEquals(2L, primaryCategoryMetrics.testimonialsCount());
    }

    @Test
    void findAllMetricsCategoriesShouldReturnEmptyListWhenAdminDoesNotExist() {
        List<CategoryMetricDTO> metrics = metricsService.findAllMetricsCategories(9999L);

        assertTrue(metrics.isEmpty());
    }

    @Test
    void getCategoryMetricsShouldReturnTestimonialsCountForAdminCategory() {
        CategoryMetricDTO metric = metricsService.getCategoryMetrics(categoryId, adminId);

        assertEquals(categoryId, metric.id());
        assertEquals("Primary Category", metric.name());
        assertEquals(2L, metric.testimonialsCount());
    }

    @Test
    void getCategoryMetricsShouldReturnZeroWhenCategoryHasNoTestimonials() {
        CategoryMetricDTO metric = metricsService.getCategoryMetrics(categoryWithoutTestimonialsId, adminId);

        assertEquals(categoryWithoutTestimonialsId, metric.id());
        assertEquals("Category Without Testimonials", metric.name());
        assertEquals(0L, metric.testimonialsCount());
    }

    @Test
    void getCategoryMetricsShouldThrowWhenCategoryDoesNotBelongToAdmin() {
        assertThrows(EntityNotFoundException.class,
                () -> metricsService.getCategoryMetrics(foreignCategoryId, adminId));
    }

    @Test
    void getCategoryMetricsShouldThrowWhenCategoryDoesNotExist() {
        assertThrows(EntityNotFoundException.class,
                () -> metricsService.getCategoryMetrics(9999L, adminId));
    }

    private void saveTestimonial(Admin admin, Category category, List<Long> tagIds) {
        testimonialService.save(
                Testimonial.builder()
                        .testimonial("Metric testimonial for ")
                        .rating(5)
                        .email("@test.com")
                        .state(StateTestimonial.DRAFT)
                        .category(category)
                        .build(),
                admin,
                null,
                null,
                tagIds
        );
    }

    @AfterEach
    void tearDown() {
        resetService.resetAll();
    }
}
