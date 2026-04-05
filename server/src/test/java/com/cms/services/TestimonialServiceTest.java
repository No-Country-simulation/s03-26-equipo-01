package com.cms.services;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.admin.Admin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    private Admin admin;
    private Testimonial testimonial;
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

        testimonial = Testimonial.builder()
                .testimonial("Excelente servicio, lo recomiendo totalmente")
                .rating(5)
                .email("user@test.com")
                .state(StateTestimonial.DRAFT)
                .build();
    }

    @Test
    public void testifyAndGetTestimonialWithoutFile() {
        Testimonial testimonialSaved = testimonialService.save(testimonial, embed.getId(), null, "https://www.youtube.com/watch?v=KhXTwEypI6c");
        Testimonial testimonialRecovered = testimonialService.findTestimonialById(testimonialSaved.getId());

        assertNotNull(testimonialSaved.getId());
        assertEquals(testimonialSaved.getId(),          testimonialRecovered.getId());
        assertEquals(testimonialSaved.getTestimonial(), testimonialRecovered.getTestimonial());
        assertEquals(testimonialSaved.getRating(),      testimonialRecovered.getRating());
        assertEquals(testimonialSaved.getEmail(),       testimonialRecovered.getEmail());
        assertEquals(testimonialSaved.getState(),       testimonialRecovered.getState());
        assertEquals(testimonialSaved.getCreatedAt(),   testimonialRecovered.getCreatedAt());
        assertEquals(embed.getId(),                     testimonialRecovered.getEmbed().getId());
        assertNull(testimonialRecovered.getImage());
    }

    @Test
    public void findTestimonialByAdminWithMultipleTestimonials() {
        Testimonial testimonial2 = Testimonial.builder()
                .testimonial("Muy buen servicio")
                .rating(4)
                .email("user2@test.com")
                .state(StateTestimonial.DRAFT)
                .build();


        Admin otherAdmin = Admin.builder()
                .email("other@test.com")
                .password("123")
                .firstName("Other")
                .lastName("Admin")
                .build();
        otherAdmin = (Admin) userService.save(otherAdmin);
        Embed otherEmbed = embedService.registerEmbed(otherAdmin.getId(), new Embed());

        Testimonial testimonialOtherAdmin = Testimonial.builder()
                .testimonial("Testimonio de otro admin")
                .rating(3)
                .email("other@test.com")
                .state(StateTestimonial.DRAFT)
                .build();

        testimonialService.save(testimonial,           embed.getId(),      null, "https://www.youtube.com/watch?v=KhXTwEypI6c");
        testimonialService.save(testimonial2,          embed.getId(),      null, "https://www.youtube.com/watch?v=KhXTwEypI6c");
        testimonialService.save(testimonialOtherAdmin, otherEmbed.getId(), null, "https://www.youtube.com/watch?v=KhXTwEypI6c");

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

        Testimonial testimonialSaved = testimonialService.save(testimonial, embed.getId(), file, "https://www.youtube.com/watch?v=KhXTwEypI6c");
        Testimonial testimonialRecovered = testimonialService.findTestimonialById(testimonialSaved.getId());

        assertNotNull(testimonialSaved.getId());
        assertEquals(testimonialSaved.getId(),          testimonialRecovered.getId());
        assertEquals(testimonialSaved.getTestimonial(), testimonialRecovered.getTestimonial());
        assertEquals(testimonialSaved.getRating(),      testimonialRecovered.getRating());
        assertEquals(testimonialSaved.getEmail(),       testimonialRecovered.getEmail());
        assertEquals(testimonialSaved.getState(),       testimonialRecovered.getState());
        assertEquals(testimonialSaved.getCreatedAt(),   testimonialRecovered.getCreatedAt());
        assertEquals(embed.getId(),                     testimonialRecovered.getEmbed().getId());
        assertNotNull(testimonialRecovered.getImage());
        assertNotNull(testimonialRecovered.getImage().getUrl());
        assertNotNull(testimonialRecovered.getImage().getPublicId());
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}