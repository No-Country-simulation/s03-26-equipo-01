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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class EmbedServiceTest {

    @Autowired private EmbedService embedService;
    @Autowired private UserService userService;
    @Autowired private ResetService resetService;
    @Autowired private CategoryService categoryService;
    @Autowired private TagService tagService;

    private Admin admin;
    private Embed embed;


    @BeforeEach
    public void setup() {
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
    public void registerEmbedSTest() {
        Admin adminRecovered = (Admin) userService.findById(admin.getId());

        assertNotNull(embed.getId());
        assertEquals(adminRecovered.getId(), embed.getAdmin().getId());
        assertTrue(adminRecovered.getEmbeds().contains(embed));
    }

    @Test
    public void registerEmbedWithDisabledAdminTest() {
        userService.disableUser(admin.getId());

        assertThrows(IllegalArgumentException.class, () ->
                embedService.registerEmbed(admin.getId(), new Embed()));
    }


    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}