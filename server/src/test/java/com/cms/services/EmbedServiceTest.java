package com.cms.services;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.embeds.Embeds;
import com.cms.model.embeds.dto.DateEmbedsRequestDTO;
import com.cms.model.embeds.dto.DateEmbedsResponseDTO;
import com.cms.model.user.impl.Admin;
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
public class EmbedServiceTest {

    @Autowired
    private EmbedService embedService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResetService resetService;

    private Admin admin;
    private DateEmbedsRequestDTO embedRequest;

    @BeforeEach
    public void setup(){
        admin = Admin.builder()
                .email("admin@test.com")
                .password("123")
                .firstName("Admin")
                .lastName("User")
                .build();

        admin = (Admin) userService.save(admin);

        embedRequest = new DateEmbedsRequestDTO();
    }

    @Test
    public void registerEmbedSTest() {
        Embeds embedSaved = embedService.registerEmbed(admin.getId(), embedRequest);

        assertNotNull(embedSaved.getId());
        assertEquals(admin.getId(), embedSaved.getAdmin().getId());
        assertTrue(admin.getEmbeds().contains(embedSaved));
    }
    @Test
    public void registerEmbedWithDisabledAdminTest() {
        userService.disableUser(admin.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            embedService.registerEmbed(admin.getId(), embedRequest);
        });
    }

    @AfterEach
    public void tearDown(){
        resetService.resetAll();
    }
}
