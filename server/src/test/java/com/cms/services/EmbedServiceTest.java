package com.cms.services;

import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Category;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.admin.Admin;
import com.cms.persistence.repository.TestimonialRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class EmbedServiceTest {

    private final EmbedService embedService;
    private final UserService userService;
    private final ResetService resetService;
    private final TestimonialService testimonialService;
    private final TestimonialRepository testimonialRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    private Admin admin;
    private Embed embed;
    private Category category;
    private List<Long> tagIds;

    public EmbedServiceTest(
            EmbedService embedService,
            UserService userService,
            ResetService resetService,
            TestimonialService testimonialService,
            TestimonialRepository testimonialRepository,
            CategoryService categoryService,
            TagService tagService
    ) {
        this.embedService = embedService;
        this.userService = userService;
        this.resetService = resetService;
        this.testimonialService = testimonialService;
        this.testimonialRepository = testimonialRepository;
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

        Tag firstTag = tagService.create(Tag.builder().name("service").build(), admin.getId());
        Tag secondTag = tagService.create(Tag.builder().name("quality").build(), admin.getId());
        tagIds = List.of(firstTag.getId(), secondTag.getId());
    }

    @Test
    public void shouldRegisterEmbed() {
        Admin recoveredAdmin = (Admin) userService.findById(admin.getId());

        assertNotNull(embed.getId());
        assertEquals(recoveredAdmin.getId(), embed.getAdmin().getId());
        assertTrue(recoveredAdmin.getEmbeds().contains(embed));
    }

    @Test
    public void shouldNotRegisterEmbedForDisabledAdmin() {
        userService.disableUser(admin.getId());

        assertThrows(IllegalArgumentException.class, () -> embedService.registerEmbed(admin.getId(), new Embed()));
    }

    @Test
    public void shouldReturnExactlyFivePublishedTestimonials() {
        createTestimonialsWithDifferentStates();
        Page<Testimonial> result = embedService.getTestimonialEmbed(0);

        assertEquals(5, result.getNumberOfElements(), "It should return exactly five testimonials");

        for (Testimonial testimonial : result.getContent()) {
            assertNotNull(testimonial.getTestimonial(), "Testimonial text must not be null");
            assertNotNull(testimonial.getWitness(), "Witness must not be null");
            assertTrue(testimonial.getRating() >= 1 && testimonial.getRating() <= 5);
            assertNotNull(testimonial.getEmail(), "Email must not be null");
            assertNotNull(testimonial.getCategory(), "Category must not be null");
            assertNotNull(testimonial.getTags(), "Tags must not be null");
        }
    }

    @Test
    public void shouldOnlyReturnPublishedTestimonials() {
        createTestimonialsWithDifferentStates();
        Page<Testimonial> result = embedService.getTestimonialEmbed(0);

        for (Testimonial testimonial : result.getContent()) {
            Testimonial original = testimonialRepository.findById(testimonial.getId());
            if (original == null) {
                throw new AssertionError("Testimonial not found for id: " + testimonial.getId());
            }
            assertEquals(
                    StateTestimonial.PUBLISHED,
                    original.getState(),
                    "Every returned testimonial must be in PUBLISHED state"
            );
        }
    }

    @Test
    public void shouldReturnEmptyPageWhenThereAreNoPublishedTestimonials() {
        createNonPublishedTestimonials();
        Page<Testimonial> result = embedService.getTestimonialEmbed(0);

        assertTrue(result.isEmpty(), "It should return an empty page when there are no published testimonials");
    }

    @Test
    public void shouldMapPublishedTestimonialsCorrectly() {
        createSpecificTestimonial("Excellent service", 5, "John Perez", "user1@test.com", StateTestimonial.PUBLISHED);
        createSpecificTestimonial("Good product", 4, "Maria Garcia", "user2@test.com", StateTestimonial.PUBLISHED);
        createSpecificTestimonial("Average support", 3, "Carlos Lopez", "user3@test.com", StateTestimonial.PUBLISHED);
        createSpecificTestimonial("Highly recommended", 5, "Ana Martinez", "user4@test.com", StateTestimonial.PUBLISHED);
        createSpecificTestimonial("Satisfied", 4, "Roberto Diaz", "user5@test.com", StateTestimonial.PUBLISHED);

        List<Testimonial> content = embedService.getTestimonialEmbed(0).getContent();
        assertEquals(5, content.size());

        assertTrue(content.stream().anyMatch(testimonial ->
                testimonial.getTestimonial().equals("Excellent service") &&
                        testimonial.getWitness().equals("John Perez") &&
                        testimonial.getRating() == 5 &&
                        testimonial.getEmail().equals("user1@test.com")), "It should find testimonial 1 with correct mapping");

        assertTrue(content.stream().anyMatch(testimonial ->
                testimonial.getTestimonial().equals("Good product") &&
                        testimonial.getWitness().equals("Maria Garcia") &&
                        testimonial.getRating() == 4 &&
                        testimonial.getEmail().equals("user2@test.com")), "It should find testimonial 2 with correct mapping");
    }

    @Test
    public void shouldPaginatePublishedTestimonials() {
        for (int index = 1; index <= 15; index++) {
            createSpecificTestimonial(
                    "Testimonial " + index,
                    5,
                    "User " + index,
                    "user" + index + "@test.com",
                    StateTestimonial.PUBLISHED
            );
        }

        assertEquals(5, embedService.getTestimonialEmbed(0).getNumberOfElements());
        assertEquals(5, embedService.getTestimonialEmbed(1).getNumberOfElements());
        assertEquals(5, embedService.getTestimonialEmbed(2).getNumberOfElements());
        assertEquals(0, embedService.getTestimonialEmbed(3).getNumberOfElements());
    }

    @Test
    public void shouldLoadRelationshipsForPublishedTestimonials() {
        createSpecificTestimonial("Test with relationships", 5, "Test User", "test@test.com", StateTestimonial.PUBLISHED);

        List<Testimonial> content = embedService.getTestimonialEmbed(0).getContent();
        assertEquals(1, content.size());

        Testimonial testimonial = content.get(0);

        assertNotNull(testimonial.getCategory(), "Category must be loaded");
        assertEquals(category.getId(), testimonial.getCategory().getId());

        assertNotNull(testimonial.getTags(), "Tags must be loaded");
        assertFalse(testimonial.getTags().isEmpty(), "It must have at least one tag");

        assertTrue(testimonial.getTags().stream().anyMatch(tag ->
                tag.getName().equals("service") || tag.getName().equals("quality")));
    }

    private void createTestimonialsWithDifferentStates() {
        for (int index = 1; index <= 5; index++) {
            createSpecificTestimonial(
                    "Published " + index,
                    index % 5 + 1,
                    "User " + index,
                    "published" + index + "@test.com",
                    StateTestimonial.PUBLISHED
            );
        }
        for (int index = 1; index <= 3; index++) {
            createSpecificTestimonial(
                    "Draft " + index,
                    index % 5 + 1,
                    "User " + index,
                    "draft" + index + "@test.com",
                    StateTestimonial.DRAFT
            );
        }
        for (int index = 1; index <= 2; index++) {
            createSpecificTestimonial(
                    "Pending " + index,
                    index % 5 + 1,
                    "User " + index,
                    "pending" + index + "@test.com",
                    StateTestimonial.PENDING
            );
        }
    }

    private void createNonPublishedTestimonials() {
        createSpecificTestimonial("Draft 1", 3, "User 1", "draft1@test.com", StateTestimonial.DRAFT);
        createSpecificTestimonial("Draft 2", 4, "User 2", "draft2@test.com", StateTestimonial.DRAFT);
        createSpecificTestimonial("Pending 1", 2, "User 3", "pending1@test.com", StateTestimonial.PENDING);
    }

    private Testimonial createSpecificTestimonial(
            String text,
            int rating,
            String witness,
            String email,
            StateTestimonial state
    ) {
        Testimonial testimonial = Testimonial.builder()
                .testimonial(text)
                .witness(witness)
                .rating(rating)
                .email(email)
                .state(state)
                .category(category)
                .build();

        return testimonialService.save(
                testimonial,
                embed.getId(),
                null,
                "https://www.youtube.com/watch?v=KhXTwEypI6c",
                tagIds
        );
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}
