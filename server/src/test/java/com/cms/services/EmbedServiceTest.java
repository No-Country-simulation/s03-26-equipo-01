package com.cms.services;

import com.cms.model.embeds.Embed;
import com.cms.model.user.impl.Admin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class EmbedServiceTest {

    @Autowired
    private EmbedService embedService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResetService resetService;

    private Admin admin;
    private Embed embed;

    @BeforeEach
    public void setup(){
        admin = Admin.builder()
                .email("admin@test.com")
                .password("123")
                .firstName("Admin")
                .lastName("User")
                .build();

        admin = (Admin) userService.save(admin);

        embed = new Embed();
    }

    @Test
    public void registerEmbedSTest() {
        Embed embedSaved = embedService.registerEmbed(admin.getId(), embed);
        Admin adminRecovered = (Admin) userService.findById(admin.getId());

        assertNotNull(embedSaved.getId());
        assertEquals(adminRecovered.getId(), embedSaved.getAdmin().getId());
        assertTrue(adminRecovered.getEmbeds().contains(embedSaved));
    }
    @Test
    public void registerEmbedWithDisabledAdminTest() {
        userService.disableUser(admin.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            embedService.registerEmbed(admin.getId(), embed);
        });
    }

    @AfterEach
    public void tearDown(){
        resetService.resetAll();
    }
}
