package com.cms.services;

import com.cms.controller.dto.testimonial.TestimonialPublicDTO;
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

        Tag tag1 = tagService.create(Tag.builder().name("servicio").build(), admin.getId());
        Tag tag2 = tagService.create(Tag.builder().name("calidad").build(), admin.getId());
        tagIds = List.of(tag1.getId(), tag2.getId());
    }

    @Test
    public void testGetTestimonialEmbed_ReturnsExactly5PublishedTestimonials() {
        createTestimonialsWithDifferentStates();
        List<Testimonial> result = embedService.getTestimonialEmbed(0);

        assertEquals(5, result.size(), "Debe devolver exactamente 5 testimonios");

        for (Testimonial testimonial : result) {
            assertNotNull(testimonial.getTestimonial(), "El testimonio no debe ser nulo");
            assertNotNull(testimonial.getWitness(), "El witness no debe ser nulo");
            assertTrue(testimonial.getRating() >= 1 && testimonial.getRating() <= 5);
            assertNotNull(testimonial.getEmail(), "El email no debe ser nulo");
            assertNotNull(testimonial.getCategory(), "La categoría no debe ser nula");
            assertNotNull(testimonial.getTags(), "Los tags no deben ser nulos");
        }
    }

    @Test
    public void testGetTestimonialEmbed_OnlyReturnsPublishedTestimonials() {
        createTestimonialsWithDifferentStates();
        List<Testimonial> result = embedService.getTestimonialEmbed(0);

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
        List<Testimonial> result = embedService.getTestimonialEmbed(0);

        assertTrue(result.isEmpty(), "Debe devolver lista vacía cuando no hay testimonios publicados");
    }

    @Test
    public void testGetTestimonialEmbed_MappingCorrectness() {
        createSpecificTestimonial("Excelente servicio", 5, "Juan Pérez", "user1@test.com", StateTestimonial.PUBLISHED);
        createSpecificTestimonial("Buen producto", 4, "María García", "user2@test.com", StateTestimonial.PUBLISHED);
        createSpecificTestimonial("Regular atención", 3, "Carlos López", "user3@test.com", StateTestimonial.PUBLISHED);
        createSpecificTestimonial("Muy recomendado", 5, "Ana Martínez", "user4@test.com", StateTestimonial.PUBLISHED);
        createSpecificTestimonial("Satisfecho", 4, "Roberto Díaz", "user5@test.com", StateTestimonial.PUBLISHED);

        List<Testimonial> result = embedService.getTestimonialEmbed(0);

        assertEquals(5, result.size());

        // Verificar que los datos mapeados coinciden con los originales
        boolean found1 = result.stream().anyMatch(t ->
                t.getTestimonial().equals("Excelente servicio") &&
                        t.getWitness().equals("Juan Pérez") &&
                        t.getRating() == 5 &&
                        t.getEmail().equals("user1@test.com"));
        assertTrue(found1, "Debe encontrar el testimonio 1 con mapeo correcto");

        boolean found2 = result.stream().anyMatch(t ->
                t.getTestimonial().equals("Buen producto") &&
                        t.getWitness().equals("María García") &&
                        t.getRating() == 4 &&
                        t.getEmail().equals("user2@test.com"));
        assertTrue(found2, "Debe encontrar el testimonio 2 con mapeo correcto");
    }

    @Test
    public void testGetTestimonialEmbed_PaginationWorks() {
        // Crear 15 testimonios publicados (3 páginas)
        for (int i = 1; i <= 15; i++) {
            createSpecificTestimonial("Testimonio " + i, 5, "Usuario " + i,
                    "user" + i + "@test.com", StateTestimonial.PUBLISHED);
        }

        // Página 0 (primeros 5)
        List<Testimonial> page0 = embedService.getTestimonialEmbed(0);
        assertEquals(5, page0.size());

        // Página 1 (siguientes 5)
        List<Testimonial> page1 = embedService.getTestimonialEmbed(1);
        assertEquals(5, page1.size());

        // Página 2 (últimos 5)
        List<Testimonial> page2 = embedService.getTestimonialEmbed(2);
        assertEquals(5, page2.size());

        // Página 3 (vacía)
        List<Testimonial> page3 = embedService.getTestimonialEmbed(3);
        assertEquals(0, page3.size());
    }

    @Test
    public void testGetTestimonialEmbed_RelationshipsLoaded() {
        createSpecificTestimonial("Test con relaciones", 5, "Test User",
                "test@test.com", StateTestimonial.PUBLISHED);

        List<Testimonial> result = embedService.getTestimonialEmbed(0);
        assertEquals(1, result.size());

        Testimonial testimonial = result.get(0);

        // Verificar que las relaciones están cargadas
        assertNotNull(testimonial.getCategory(), "La categoría debe estar cargada");
        assertEquals(category.getId(), testimonial.getCategory().getId());

        assertNotNull(testimonial.getTags(), "Los tags deben estar cargados");
        assertFalse(testimonial.getTags().isEmpty(), "Debe tener al menos un tag");

        // Verificar que los tags tienen los datos esperados
        assertTrue(testimonial.getTags().stream().anyMatch(tag ->
                tag.getName().equals("servicio") || tag.getName().equals("calidad")));
    }

    private void createTestimonialsWithDifferentStates() {
        for (int i = 1; i <= 5; i++)
            createSpecificTestimonial("Publicado " + i, i % 5 + 1, "Usuario " + i,
                    "pub" + i + "@test.com", StateTestimonial.PUBLISHED);
        for (int i = 1; i <= 3; i++)
            createSpecificTestimonial("Draft " + i, i % 5 + 1, "Usuario " + i,
                    "draft" + i + "@test.com", StateTestimonial.DRAFT);
        for (int i = 1; i <= 2; i++)
            createSpecificTestimonial("Archivado " + i, i % 5 + 1, "Usuario " + i,
                    "arch" + i + "@test.com", StateTestimonial.PENDING);
    }

    private void createNonPublishedTestimonials() {
        createSpecificTestimonial("Draft 1", 3, "Usuario 1", "draft1@test.com", StateTestimonial.DRAFT);
        createSpecificTestimonial("Draft 2", 4, "Usuario 2", "draft2@test.com", StateTestimonial.DRAFT);
        createSpecificTestimonial("Archived 1", 2, "Usuario 3", "archived1@test.com", StateTestimonial.PENDING);
    }

    private Testimonial createSpecificTestimonial(String text, int rating, String witness,
                                                  String email, StateTestimonial state) {
        Testimonial t = Testimonial.builder()
                .testimonial(text)
                .witness(witness)
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