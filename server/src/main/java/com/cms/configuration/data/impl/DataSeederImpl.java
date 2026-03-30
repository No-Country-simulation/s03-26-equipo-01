package com.cms.configuration.data.impl;

import com.cms.configuration.data.DataSeeder;
import com.cms.model.embeds.Embed;
import com.cms.model.user.impl.Admin;
import com.cms.model.user.impl.Editor;
import com.cms.services.EmbedService;
import com.cms.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DataSeederImpl implements DataSeeder {

    private final UserService userService;

    private final EmbedService embedService;

    private Editor editor;

    private Admin admin;

    public DataSeederImpl(UserService userService, EmbedService embedService) {
        this.userService = userService;
        this.embedService = embedService;
    }

    @Override
    public void run(String... args) {
        editor = Editor.builder()
                .email("editor@gmail.com")
                .password("123")
                .firstName("editor")
                .lastName("edita")
                .build();
        admin = Admin.builder()
                .email("admin@gmail.com")
                .password("123")
                .firstName("admin")
                .lastName("administra")
                .build();

        Admin adminSaved = (Admin) userService.save(admin);

        embedService.registerEmbed(adminSaved.getId(), new Embed());

        userService.save(editor);

    }
}
