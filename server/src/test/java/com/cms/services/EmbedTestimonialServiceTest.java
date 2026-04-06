package com.cms.services;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.Admin;
import com.cms.persistence.TestimonialRepository;
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
    @Autowired
    private ResetService resetService;

    @Autowired
    private UserService userService;

    @Autowired
    private TestimonialService testimonialService;

    @Autowired
    private TestimonialRepository testimonialRepository;
    @Autowired
    private EmbedService embedService;

    private Admin admin;
    private Embed embed;

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
    }

    @Test
    public void testGetTestimonialEmbed_ReturnsExactly5PublishedTestimonials() {
        // Crear 10 testimonios: 5 PUBLISHED, 3 DRAFT, 2 ARCHIVED y lo guarda en la BD
        createTestimonialsWithDifferentStates();

        List<Testimonial> result = embedService.getTestimonialEmbed();

         assertEquals(5, result.size(), "Debe devolver exactamente 5 testimonios");

        // Verificar el mapeo
        for (Testimonial testimonial : result) {
            assertNotNull(testimonial.getTestimonial(), "El testimonio no debe ser nulo");
            assertTrue(testimonial.getRating() >= 1 && testimonial.getRating() <= 5, "El rating debe estar entre 1 y 5");//suponiendo un maximo de 5 estrellas
            assertNotNull(testimonial.getEmail(), "El email no debe ser nulo");
        }
    }

    @Test
    public void testGetTestimonialEmbed_OnlyReturnsPublishedTestimonials() {
        createTestimonialsWithDifferentStates();
        List<Testimonial> result = embedService.getTestimonialEmbed();

        for (Testimonial testimonial : result) {

//            Busca en la BD
            Testimonial originalTestimonial = testimonialRepository.findById(testimonial.getId());
                    if(originalTestimonial == null){
                        throw new AssertionError("No se encontró el testimonio con ID: " + testimonial.getId());
                    };
            assertEquals(StateTestimonial.PUBLISHED, originalTestimonial.getState(),
                    "Todos los testimonios devueltos deben tener estado PUBLISHED");
        }
    }

    @Test
    public void testGetTestimonialEmbed_EmptyWhenNoPublishedTestimonials() {
        // Crea testimonios no publicados
        createNonPublishedTestimonials();

        List<Testimonial> result = embedService.getTestimonialEmbed();

        assertTrue(result.isEmpty(), "Debe devolver lista vacía cuando no hay testimonios publicados");
    }

    @Test
    public void testGetTestimonialEmbed_MappingCorrectness() {
        // Crear testimonios publicados con datos específicos
        Testimonial published1 = createSpecificTestimonial("Excelente servicio", 5, "user1@test.com", StateTestimonial.PUBLISHED);
        Testimonial published2 = createSpecificTestimonial("Buen producto", 4, "user2@test.com", StateTestimonial.PUBLISHED);
        Testimonial published3 = createSpecificTestimonial("Regular atención", 3, "user3@test.com", StateTestimonial.PUBLISHED);
        Testimonial published4 = createSpecificTestimonial("Muy recomendado", 5, "user4@test.com", StateTestimonial.PUBLISHED);
        Testimonial published5 = createSpecificTestimonial("Satisfecho", 4, "user5@test.com", StateTestimonial.PUBLISHED);

        List<Testimonial> result = embedService.getTestimonialEmbed();

        assertEquals(5, result.size());

        // Verificar que los datos mapeados coinciden con los originales
        boolean found1 = result.stream().anyMatch(testimonial ->
                testimonial.getTestimonial().equals("Excelente servicio") &&
                        testimonial.getRating() == 5 &&
                        testimonial.getEmail().equals("user1@test.com"));
        assertTrue(found1, "Debe encontrar el testimonio 1 con mapeo correcto");

        boolean found2 = result.stream().anyMatch(testimonial ->
                testimonial.getTestimonial().equals("Buen producto") &&
                        testimonial.getRating() == 4 &&
                        testimonial.getEmail().equals("user2@test.com"));
        assertTrue(found2, "Debe encontrar el testimonio 2 con mapeo correcto");
    }

    private void createTestimonialsWithDifferentStates() {
        // Crear 5 testimonios PUBLISHED
        for (int i = 1; i <= 5; i++) {
            createSpecificTestimonial(
                    "Testimonio publicado " + i,
                    i % 5 + 1, // ratings 1-5
                    "published" + i + "@test.com",
                    StateTestimonial.PUBLISHED
            );
        }
        // Crear 3 testimonios DRAFT
        for (int i = 1; i <= 3; i++) {
            createSpecificTestimonial(
                    "Testimonio draft " + i,
                    i % 5 + 1,
                    "draft" + i + "@test.com",
                    StateTestimonial.DRAFT
            );
        }
        // Crear 2 testimonios ARCHIVED
        for (int i = 1; i <= 2; i++) {
            createSpecificTestimonial(
                    "Testimonio archivado " + i,
                    i % 5 + 1,
                    "archived" + i + "@test.com",
                    StateTestimonial.ARCHIVED
            );
        }}
        private void createNonPublishedTestimonials () {
            // Crear solo testimonios no publicados
            createSpecificTestimonial("Draft 1", 3, "draft1@test.com", StateTestimonial.DRAFT);
            createSpecificTestimonial("Draft 2", 4, "draft2@test.com", StateTestimonial.DRAFT);
            createSpecificTestimonial("Archived 1", 2, "archived1@test.com", StateTestimonial.ARCHIVED);
        }
        private Testimonial createSpecificTestimonial (String testimonialText,int rating, String email, StateTestimonial
        state){
            Testimonial testimonial = Testimonial.builder()
                    .testimonial(testimonialText)
                    .rating(rating)
                    .email(email)
                    .state(state)
                    .build();

            return testimonialService.save(testimonial, embed.getId());
        }
        @AfterEach
        public void tearDown () {
            resetService.resetAll();
        }
    }


