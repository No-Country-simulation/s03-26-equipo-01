package com.cms.services;

import com.cms.model.embed.Embed;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class TestimonialServiceTest {


    @Autowired
    private ResetService resetService;

    @Autowired
    private TestimonialService testimonialService;

    @Autowired
    private EmbedService embedService;

    private Testimonial testimonial;

    private Embed embed;

    @BeforeEach
    public void setUp() {

        embed = embedService.save(new Embed());

        testimonial = Testimonial.builder()
                .testimonial("Excelente servicio, lo recomiendo totalmente")
                .rating(5)
                .email("user@test.com")
                .state(StateTestimonial.DRAFT)
                .build();
    }

    @Test
    public void testifyAndGetTestimonial() {
        Testimonial testimonialSaved = testimonialService.save(testimonial, embed.getId());

        Testimonial testimonialRecovered = testimonialService.findTestimonialById(testimonialSaved.getId());

        assertEquals(testimonialSaved.getId(),testimonialRecovered.getId());
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}
