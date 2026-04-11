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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class TestimonialServiceTest {

    @Autowired
    private ResetService resetService;

    @Autowired
    private UserService userService;

    @Autowired
    private TestimonialService testimonialService;

    @Autowired
    private EmbedService embedService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    private Admin admin;
    private Testimonial testimonial;
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

        Tag tag1 = tagService.create(Tag.builder().name("backend").build(), admin.getId());
        Tag tag2 = tagService.create(Tag.builder().name("java").build(), admin.getId());
        tagIds = List.of(tag1.getId(), tag2.getId());

        testimonial = Testimonial.builder()
                .testimonial("Excelente servicio, lo recomiendo totalmente")
                .rating(5)
                .email("user@test.com")
                .state(StateTestimonial.PUBLISHED)
                .build();
    }

    @Test
    public void testifyAndGetTestimonialWithoutFile() {
        Testimonial testimonialSaved = testimonialService.save(testimonial,admin, null, "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds);
        Testimonial testimonialRecovered = testimonialService.findTestimonialById(testimonialSaved.getId());

        assertNotNull(testimonialSaved.getId());
        assertEquals(testimonialSaved.getId(),          testimonialRecovered.getId());
        assertEquals(testimonialSaved.getTestimonial(), testimonialRecovered.getTestimonial());
        assertEquals(testimonialSaved.getRating(),      testimonialRecovered.getRating());
        assertEquals(testimonialSaved.getEmail(),       testimonialRecovered.getEmail());
        assertEquals(testimonialSaved.getState(),       testimonialRecovered.getState());
        assertEquals(testimonialSaved.getCreatedAt(),   testimonialRecovered.getCreatedAt());
    }

    @Test
    public void findTestimonialByAdminWithMultipleTestimonials() {
        Testimonial testimonial2 = Testimonial.builder()
                .testimonial("Muy buen servicio")
                .rating(4)
                .email("user2@test.com")
                .state(StateTestimonial.PUBLISHED)
                .build();

        Admin otherAdmin = Admin.builder()
                .email("other@test.com")
                .password("123")
                .firstName("Other")
                .lastName("Admin")
                .build();
        otherAdmin = (Admin) userService.save(otherAdmin);

        Testimonial testimonialOtherAdmin = Testimonial.builder()
                .testimonial("Testimonio de otro admin")
                .rating(3)
                .email("other@test.com")
                .state(StateTestimonial.PUBLISHED)
                .build();

        testimonialService.save(testimonial, admin, null, "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds);
        testimonialService.save(testimonial2,admin, null, "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds);
        testimonialService.save(testimonialOtherAdmin, otherAdmin, null, "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds);

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
        InputStream is = getClass().getClassLoader().getResourceAsStream("logo.jpg");
        if (is == null) throw new IllegalStateException("logo.jpg no encontrado en src/test/resources/");

        MockMultipartFile file = new MockMultipartFile(
                "image",
                "logo.jpg",
                "image/jpeg",
                is.readAllBytes()
        );

        Testimonial testimonialSaved = testimonialService.save(testimonial, admin, file, "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds);
        Testimonial testimonialRecovered = testimonialService.findTestimonialById(testimonialSaved.getId());

        assertNotNull(testimonialSaved.getId());
        assertEquals(testimonialSaved.getId(),          testimonialRecovered.getId());
        assertEquals(testimonialSaved.getTestimonial(), testimonialRecovered.getTestimonial());
        assertEquals(testimonialSaved.getRating(),      testimonialRecovered.getRating());
        assertEquals(testimonialSaved.getEmail(),       testimonialRecovered.getEmail());
        assertEquals(testimonialSaved.getState(),       testimonialRecovered.getState());
        assertEquals(testimonialSaved.getCreatedAt(),   testimonialRecovered.getCreatedAt());
        assertNotNull(testimonialRecovered.getMedia());
        assertNotNull(testimonialRecovered.getMedia().getUrl());
        assertNotNull(testimonialRecovered.getMedia().getPublicId());
    }

    @Test
    public void advanceByEditor(){
        Testimonial testimonial2 = testimonialService.save(Testimonial.builder()
                .testimonial("Excelente servicio, lo recomiendo totalmente")
                .rating(5)
                .email("user@test.com")
                .state(StateTestimonial.DRAFT)
                .build(), admin, null,"https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds);

        testimonialService.advanceByEditor(testimonial2.getId());

        Testimonial recovered = testimonialService.findTestimonialById(testimonial2.getId());

        assertEquals(StateTestimonial.PENDING, recovered.getState());

        assertThrows(BusinessException.class, () -> testimonialService.advanceByEditor(recovered.getId()));
    }

    @Test
    public void findAllTestimonialPublishedReturnsOnlyPublishedForAdmin() {
        testimonialService.save(Testimonial.builder()
                        .testimonial("Publicado 1").rating(5).email("pub1@test.com")
                        .state(StateTestimonial.PUBLISHED).build(),
                admin, null, "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds);

        testimonialService.save(Testimonial.builder()
                        .testimonial("Publicado 2").rating(4).email("pub2@test.com")
                        .state(StateTestimonial.PUBLISHED).build(),
                admin, null, "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds);

        testimonialService.save(Testimonial.builder()
                        .testimonial("Borrador").rating(3).email("draft@test.com")
                        .state(StateTestimonial.DRAFT).build(),
                admin, null, "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds);

        Admin otherAdmin = (Admin) userService.save(Admin.builder()
                .email("other@test.com").password("123")
                .firstName("Other").lastName("Admin").build());

        testimonialService.save(Testimonial.builder()
                        .testimonial("Publicado de otro admin").rating(5).email("other@test.com")
                        .state(StateTestimonial.PUBLISHED).build(),
                otherAdmin, null, "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds);

        Page<Testimonial> result = testimonialService.findAllTestimonialPublished(0, 5, admin);

        assertEquals(2, result.getTotalElements());
        assertTrue(result.getContent().stream()
                .allMatch(t -> t.getState() == StateTestimonial.PUBLISHED));
        assertTrue(result.getContent().stream()
                .allMatch(t -> t.getAdmin().getId().equals(admin.getId())));
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}
