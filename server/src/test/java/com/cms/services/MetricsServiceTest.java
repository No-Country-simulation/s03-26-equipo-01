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

    private Long tagId;
    private Long categoryId;

    @BeforeEach
    void setUp() {
        Admin admin = (Admin) userService.save(Admin.builder()
                .email("metrics-admin@test.com")
                .password("123")
                .firstName("Metrics")
                .lastName("Admin")
                .build());

        Embed embed = embedService.registerEmbed(admin.getId(), new Embed());

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

        Tag primaryTag = tagService.create(Tag.builder().name("Primary Tag").build(), admin.getId());
        Tag secondaryTag = tagService.create(Tag.builder().name("Secondary Tag").build(), admin.getId());

        tagId = primaryTag.getId();
        categoryId = primaryCategory.getId();

        saveTestimonial(embed, primaryCategory, List.of(primaryTag));
        saveTestimonial(embed, primaryCategory, List.of(primaryTag, secondaryTag));
        saveTestimonial(embed, secondaryCategory, List.of(secondaryTag));
    }

    @Test
    void getTagMetricsShouldReturnTestimonialsCountForRequestedTag() {
        TagMetricDTO metrics = metricsService.getTagMetrics(tagId);

        assertEquals(tagId, metrics.id());
        assertEquals("primary tag", metrics.name());
        assertEquals("primary-tag", metrics.slug());
        assertEquals(2L, metrics.testimonialsCount());
    }

    @Test
    void getCategoryMetricsShouldReturnTestimonialsCountForRequestedCategory() {
        CategoryMetricDTO metrics = metricsService.getCategoryMetrics(categoryId);

        assertEquals(categoryId, metrics.id());
        assertEquals("Primary Category", metrics.name());
        assertEquals("primary-category", metrics.slug());
        assertEquals(2L, metrics.testimonialsCount());
    }

    @Test
    void getTestimonialsMetricsShouldReturnBothCountersInSingleResponse() {
        MetricsResponseDTO metrics = metricsService.getTestimonialsMetrics(tagId, categoryId);

        assertEquals(2L, metrics.tag().testimonialsCount());
        assertEquals(2L, metrics.category().testimonialsCount());
    }

    @Test
    void getTagMetricsShouldFailWhenTagDoesNotExist() {
        assertThrows(EntityNotFoundException.class, () -> metricsService.getTagMetrics(9999L));
    }

    @Test
    void getCategoryMetricsShouldFailWhenCategoryDoesNotExist() {
        assertThrows(EntityNotFoundException.class, () -> metricsService.getCategoryMetrics(9999L));
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
