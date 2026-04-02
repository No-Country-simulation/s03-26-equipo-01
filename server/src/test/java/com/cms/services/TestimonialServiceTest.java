package com.cms.services;

import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.Admin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testifyAndGetTestimonial() {
        Testimonial testimonialSaved = testimonialService.save(testimonial, embed.getId(), null);

        Testimonial testimonialRecovered = testimonialService.findTestimonialById(testimonialSaved.getId());

        assertEquals(testimonialSaved.getId(),testimonialRecovered.getId());
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}
