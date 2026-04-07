package com.cms.services;

import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Category;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.admin.Admin;
import com.cms.persistence.repository.TestimonialRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class EmbedTestimonialServiceTest {

    @Autowired private ResetService resetService;
    @Autowired private UserService userService;
    @Autowired private TestimonialService testimonialService;
    @Autowired private TestimonialRepository testimonialRepository;
    @Autowired private EmbedService embedService;
    @Autowired private CategoryService categoryService;
    @Autowired private TagService tagService;

    private Admin admin;
    private Embed embed;
    private Category category;
    private List<Long> tagIds;

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

        Tag tag1 = tagService.create(Tag.builder().name("tag1").build(), admin.getId());
        tagIds = List.of(tag1.getId());
    }

    @Test
    public void testGetTestimonialEmbed_ReturnsExactly5PublishedTestimonials() {
        createTestimonialsWithDifferentStates();
        List<Testimonial> result = embedService.getTestimonialEmbed();

        assertEquals(5, result.size(), "Debe devolver exactamente 5 testimonios");

        for (Testimonial testimonial : result) {
            assertNotNull(testimonial.getTestimonial(), "El testimonio no debe ser nulo");
            assertTrue(testimonial.getRating() >= 1 && testimonial.getRating() <= 5);
            assertNotNull(testimonial.getEmail(), "El email no debe ser nulo");
        }
    }

    @Test
    public void testGetTestimonialEmbed_OnlyReturnsPublishedTestimonials() {
        createTestimonialsWithDifferentStates();
        List<Testimonial> result = embedService.getTestimonialEmbed();

        for (Testimonial testimonial : result) {
            Testimonial original = testimonialRepository.findById(testimonial.getId());
            if (original == null) {
                throw new AssertionError("No se encontró el testimonio con ID: " + testimonial.getId());
            }
            assertEquals(StateTestimonial.PUBLISHED, original.getState(),
                    "Todos los testimonios devueltos deben tener estado PUBLISHED");
        }
    }

    @Test
    public void testGetTestimonialEmbed_EmptyWhenNoPublishedTestimonials() {
        createNonPublishedTestimonials();
        List<Testimonial> result = embedService.getTestimonialEmbed();

        assertTrue(result.isEmpty(), "Debe devolver lista vacía cuando no hay testimonios publicados");
    }

    @Test
    public void testGetTestimonialEmbed_MappingCorrectness() {
        createSpecificTestimonial("Excelente servicio", 5, "user1@test.com", StateTestimonial.PUBLISHED);
        createSpecificTestimonial("Buen producto",      4, "user2@test.com", StateTestimonial.PUBLISHED);
        createSpecificTestimonial("Regular atención",   3, "user3@test.com", StateTestimonial.PUBLISHED);
        createSpecificTestimonial("Muy recomendado",    5, "user4@test.com", StateTestimonial.PUBLISHED);
        createSpecificTestimonial("Satisfecho",         4, "user5@test.com", StateTestimonial.PUBLISHED);

        List<Testimonial> result = embedService.getTestimonialEmbed();

        assertEquals(5, result.size());

        boolean found1 = result.stream().anyMatch(t ->
                t.getTestimonial().equals("Excelente servicio") &&
                        t.getRating() == 5 &&
                        t.getEmail().equals("user1@test.com"));
        assertTrue(found1);

        boolean found2 = result.stream().anyMatch(t ->
                t.getTestimonial().equals("Buen producto") &&
                        t.getRating() == 4 &&
                        t.getEmail().equals("user2@test.com"));
        assertTrue(found2);
    }

    private void createTestimonialsWithDifferentStates() {
        for (int i = 1; i <= 5; i++)
            createSpecificTestimonial("Publicado " + i,  i % 5 + 1, "pub"  + i + "@test.com", StateTestimonial.PUBLISHED);
        for (int i = 1; i <= 3; i++)
            createSpecificTestimonial("Draft " + i,      i % 5 + 1, "draft"+ i + "@test.com", StateTestimonial.DRAFT);
        for (int i = 1; i <= 2; i++)
            createSpecificTestimonial("Archivado " + i,  i % 5 + 1, "arch" + i + "@test.com", StateTestimonial.DRAFT);
    }

    private void createNonPublishedTestimonials() {
        createSpecificTestimonial("Draft 1",    3, "draft1@test.com",    StateTestimonial.DRAFT);
        createSpecificTestimonial("Draft 2",    4, "draft2@test.com",    StateTestimonial.DRAFT);
        createSpecificTestimonial("Archived 1", 2, "archived1@test.com", StateTestimonial.DRAFT);
    }

    private Testimonial createSpecificTestimonial(String text, int rating, String email, StateTestimonial state) {
        Testimonial t = Testimonial.builder()
                .testimonial(text)
                .rating(rating)
                .email(email)
                .state(state)
                .build();

        return testimonialService.save(t, embed.getId(), null,
                "https://www.youtube.com/watch?v=KhXTwEypI6c",
                category.getId(), tagIds);
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}