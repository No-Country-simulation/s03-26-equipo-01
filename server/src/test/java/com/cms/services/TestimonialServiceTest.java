package com.cms.services;

import com.cms.exception.EntityNotFoundException;
import com.cms.exception.business.BusinessException;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Category;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.admin.Admin;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

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
        admin = (Admin) userService.save(Admin.builder()
                .email("admin@test.com")
                .password("123")
                .firstName("Admin")
                .lastName("User")
                .build());

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
                .testimonial("Excellent service, highly recommended")
                .witness("John Doe")
                .rating(5)
                .email("user@test.com")
                .state(StateTestimonial.PUBLISHED)
                .category(category)
                .build();
    }

    @Test
    public void testifyAndGetTestimonialWithoutFile() {
        Testimonial testimonialSaved = testimonialService.save(testimonial, embed.getId(), null, "", tagIds);
        Testimonial testimonialRecovered = testimonialService.findTestimonialById(testimonialSaved.getId());

        assertNotNull(testimonialSaved.getId());
        assertEquals(testimonialSaved.getId(), testimonialRecovered.getId());
        assertEquals(testimonialSaved.getTestimonial(), testimonialRecovered.getTestimonial());
        assertEquals(testimonialSaved.getRating(), testimonialRecovered.getRating());
        assertEquals(testimonialSaved.getEmail(), testimonialRecovered.getEmail());
        assertEquals(testimonialSaved.getState(), testimonialRecovered.getState());
        assertEquals(testimonialSaved.getCreatedAt(), testimonialRecovered.getCreatedAt());
        assertEquals(embed.getId(), testimonialRecovered.getEmbed().getId());
    }

    @Test
    public void findTestimonialByAdminWithMultipleTestimonials() {
        Testimonial secondTestimonial = Testimonial.builder()
                .testimonial("Very good service")
                .witness("Jane Doe")
                .rating(4)
                .email("user2@test.com")
                .state(StateTestimonial.PUBLISHED)
                .category(category)
                .build();

        Admin otherAdmin = (Admin) userService.save(Admin.builder()
                .email("other@test.com")
                .password("123")
                .firstName("Other")
                .lastName("Admin")
                .build());
        Embed otherEmbed = embedService.registerEmbed(otherAdmin.getId(), new Embed());

        Category otherCategory = categoryService.create(
                Category.builder()
                        .name("Other Category")
                        .slug("other-category")
                        .description("Category for another admin")
                        .build(),
                otherAdmin.getId()
        );

        Testimonial otherAdminTestimonial = Testimonial.builder()
                .testimonial("Another admin testimonial")
                .witness("Other User")
                .rating(3)
                .email("other@test.com")
                .state(StateTestimonial.PUBLISHED)
                .category(otherCategory)
                .build();

        testimonialService.save(testimonial, embed.getId(), null, "", tagIds);
        testimonialService.save(secondTestimonial, embed.getId(), null, "", tagIds);
        testimonialService.save(otherAdminTestimonial, otherEmbed.getId(), null, "", tagIds);

        List<Testimonial> testimonials = testimonialService.findTestimonialByAdmin(admin.getId());

        assertEquals(2, testimonials.size());
    }

    @Test
    public void findTestimonialByAdminNotFound() {
        assertThrows(EntityNotFoundException.class, () ->
                testimonialService.findTestimonialByAdmin(-1L)
        );
    }

    @Test
    public void testifyWithImage() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("logo.jpg");
        if (inputStream == null) {
            throw new IllegalStateException("logo.jpg was not found in src/test/resources");
        }

        MockMultipartFile file = new MockMultipartFile(
                "image",
                "logo.jpg",
                "image/jpeg",
                inputStream.readAllBytes()
        );

        Testimonial testimonialSaved = testimonialService.save(testimonial, embed.getId(), file, "", tagIds);
        Testimonial testimonialRecovered = testimonialService.findTestimonialById(testimonialSaved.getId());

        assertNotNull(testimonialSaved.getId());
        assertEquals(testimonialSaved.getId(), testimonialRecovered.getId());
        assertEquals(testimonialSaved.getTestimonial(), testimonialRecovered.getTestimonial());
        assertEquals(testimonialSaved.getRating(), testimonialRecovered.getRating());
        assertEquals(testimonialSaved.getEmail(), testimonialRecovered.getEmail());
        assertEquals(testimonialSaved.getState(), testimonialRecovered.getState());
        assertEquals(testimonialSaved.getCreatedAt(), testimonialRecovered.getCreatedAt());
        assertEquals(embed.getId(), testimonialRecovered.getEmbed().getId());
        assertNotNull(testimonialRecovered.getMedia());
        assertNotNull(testimonialRecovered.getMedia().getUrl());
        assertNotNull(testimonialRecovered.getMedia().getPublicId());
    }

    @Test
    public void advanceByEditor() {
        Testimonial secondTestimonial = testimonialService.save(
                Testimonial.builder()
                        .testimonial("Excellent service, highly recommended")
                        .witness("John Doe")
                        .rating(5)
                        .email("user@test.com")
                        .state(StateTestimonial.DRAFT)
                        .category(category)
                        .build(),
                embed.getId(),
                null,
                "",
                tagIds
        );

        testimonialService.advanceByEditor(secondTestimonial.getId());

        Testimonial recovered = testimonialService.findTestimonialById(secondTestimonial.getId());

        assertEquals(StateTestimonial.PENDING, recovered.getState());

        assertThrows(BusinessException.class, () -> testimonialService.advanceByEditor(recovered.getId()));
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}
