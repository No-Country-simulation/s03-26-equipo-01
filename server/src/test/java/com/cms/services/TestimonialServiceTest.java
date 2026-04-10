package com.cms.services;

import com.cms.exception.EntityNotFoundException;
import com.cms.exception.business.BusinessException;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Category;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.admin.Admin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class TestimonialServiceTest {

    private final ResetService resetService;
    private final UserService userService;
    private final TestimonialService testimonialService;
    private final EmbedService embedService;
    private final CategoryService categoryService;
    private final TagService tagService;

    private Admin admin;
    private Testimonial testimonial;
    private Embed embed;
    private Category category;
    private List<Long> tagIds;

    public TestimonialServiceTest(
            ResetService resetService,
            UserService userService,
            TestimonialService testimonialService,
            EmbedService embedService,
            CategoryService categoryService,
            TagService tagService
    ) {
        this.resetService = resetService;
        this.userService = userService;
        this.testimonialService = testimonialService;
        this.embedService = embedService;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }

    @BeforeEach
    public void setUp() {
        admin = Admin.builder()
                .email("admin@test.com")
                .password("123")
                .firstName("Admin")
                .lastName("User")
                .build();

        admin = (Admin) userService.save(admin);
        embed = embedService.registerEmbed(admin.getId(), new Embed());

        category = categoryService.create(
                Category.builder()
                        .name("Test Category")
                        .slug("test-category")
                        .description("Category for tests")
                        .build(),
                admin.getId()
        );

        Tag firstTag = tagService.create(Tag.builder().name("backend").build(), admin.getId());
        Tag secondTag = tagService.create(Tag.builder().name("java").build(), admin.getId());
        tagIds = List.of(firstTag.getId(), secondTag.getId());

        testimonial = Testimonial.builder()
                .testimonial("Excellent service, I fully recommend it")
                .rating(5)
                .email("user@test.com")
                .state(StateTestimonial.PUBLISHED)
                .category(category)
                .build();
    }

    @Test
    public void shouldSaveAndRecoverTestimonialWithoutFile() {
        Testimonial savedTestimonial = testimonialService.save(
                testimonial,
                embed.getId(),
                null,
                "https://www.youtube.com/watch?v=KhXTwEypI6c",
                tagIds
        );
        Testimonial recoveredTestimonial = testimonialService.findTestimonialById(savedTestimonial.getId());

        assertNotNull(savedTestimonial.getId());
        assertEquals(savedTestimonial.getId(), recoveredTestimonial.getId());
        assertEquals(savedTestimonial.getTestimonial(), recoveredTestimonial.getTestimonial());
        assertEquals(savedTestimonial.getRating(), recoveredTestimonial.getRating());
        assertEquals(savedTestimonial.getEmail(), recoveredTestimonial.getEmail());
        assertEquals(savedTestimonial.getState(), recoveredTestimonial.getState());
        assertEquals(savedTestimonial.getCreatedAt(), recoveredTestimonial.getCreatedAt());
        assertEquals(embed.getId(), recoveredTestimonial.getEmbed().getId());
    }

    @Test
    public void shouldFindTestimonialsByAdminWithMultipleTestimonials() {
        Testimonial secondTestimonial = Testimonial.builder()
                .testimonial("Very good service")
                .rating(4)
                .email("user2@test.com")
                .state(StateTestimonial.PUBLISHED)
                .category(category)
                .build();

        Admin otherAdmin = Admin.builder()
                .email("other@test.com")
                .password("123")
                .firstName("Other")
                .lastName("Admin")
                .build();
        otherAdmin = (Admin) userService.save(otherAdmin);
        Embed otherEmbed = embedService.registerEmbed(otherAdmin.getId(), new Embed());

        Testimonial otherAdminTestimonial = Testimonial.builder()
                .testimonial("Testimonial from another admin")
                .rating(3)
                .email("other@test.com")
                .state(StateTestimonial.PUBLISHED)
                .category(category)
                .build();

        testimonialService.save(testimonial, embed.getId(), null, "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds);
        testimonialService.save(secondTestimonial, embed.getId(), null, "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds);
        testimonialService.save(otherAdminTestimonial, otherEmbed.getId(), null, "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds);

        List<Testimonial> testimonials = testimonialService.findTestimonialByAdmin(admin.getId());

        assertEquals(2, testimonials.size());
    }

    @Test
    public void shouldThrowWhenAdminIsNotFound() {
        assertThrows(EntityNotFoundException.class, () -> testimonialService.findTestimonialByAdmin(-1L));
    }

    @Test
    public void shouldSaveTestimonialWithImage() throws Exception {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("logo.jpg")) {
            if (inputStream == null) {
                throw new IllegalStateException("logo.jpg was not found in src/test/resources");
            }

            MockMultipartFile file = new MockMultipartFile(
                    "image",
                    "logo.jpg",
                    "image/jpeg",
                    inputStream.readAllBytes()
            );

            Testimonial savedTestimonial = testimonialService.save(
                    testimonial,
                    embed.getId(),
                    file,
                    "https://www.youtube.com/watch?v=KhXTwEypI6c",
                    tagIds
            );
            Testimonial recoveredTestimonial = testimonialService.findTestimonialById(savedTestimonial.getId());

            assertNotNull(savedTestimonial.getId());
            assertEquals(savedTestimonial.getId(), recoveredTestimonial.getId());
            assertEquals(savedTestimonial.getTestimonial(), recoveredTestimonial.getTestimonial());
            assertEquals(savedTestimonial.getRating(), recoveredTestimonial.getRating());
            assertEquals(savedTestimonial.getEmail(), recoveredTestimonial.getEmail());
            assertEquals(savedTestimonial.getState(), recoveredTestimonial.getState());
            assertEquals(savedTestimonial.getCreatedAt(), recoveredTestimonial.getCreatedAt());
            assertEquals(embed.getId(), recoveredTestimonial.getEmbed().getId());
            assertNotNull(recoveredTestimonial.getMedia());
            assertNotNull(recoveredTestimonial.getMedia().getUrl());
            assertNotNull(recoveredTestimonial.getMedia().getPublicId());
        }
    }

    @Test
    public void shouldAdvanceByEditor() {
        Testimonial draftTestimonial = testimonialService.save(
                Testimonial.builder()
                        .testimonial("Excellent service, I fully recommend it")
                        .rating(5)
                        .email("user@test.com")
                        .state(StateTestimonial.DRAFT)
                        .category(category)
                        .build(),
                embed.getId(),
                null,
                "https://www.youtube.com/watch?v=KhXTwEypI6c",
                tagIds
        );

        testimonialService.advanceByEditor(draftTestimonial.getId());

        Testimonial recovered = testimonialService.findTestimonialById(draftTestimonial.getId());

        assertEquals(StateTestimonial.PENDING, recovered.getState());
        assertThrows(BusinessException.class, () -> testimonialService.advanceByEditor(recovered.getId()));
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}
