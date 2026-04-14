package com.cms.controller;

import com.cms.model.testimonial.Tag;
import com.cms.model.user.User;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
import com.cms.security.user.UserDetailsImpl;
import com.cms.services.ResetService;
import com.cms.services.TagService;
import com.cms.services.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class TagControllerIntegrationTest {

    private final MockMvc mockMvc;
    private final UserService userService;
    private final TagService tagService;
    private final ResetService resetService;

    @Test
    void findAllShouldReturnOnlyTagsBelongingToAuthenticatedAdmin() throws Exception {
        Admin admin = saveAdmin("admin-tags@test.com");
        Admin otherAdmin = saveAdmin("other-admin-tags@test.com");
        Tag backendTag = tagService.create(Tag.builder().name("backend").build(), admin.getId());
        Tag javaTag = tagService.create(Tag.builder().name("java").build(), admin.getId());
        tagService.create(Tag.builder().name("qa").build(), otherAdmin.getId());

        mockMvc.perform(get("/api/v1/tags")
                        .contextPath("/api/v1")
                        .with(authentication(authenticationFor(admin))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(backendTag.getId()))
                .andExpect(jsonPath("$[0].name").value("backend"))
                .andExpect(jsonPath("$[1].id").value(javaTag.getId()))
                .andExpect(jsonPath("$[1].name").value("java"));
    }

    @Test
    void findAllShouldReturnForbiddenForEditor() throws Exception {
        Admin admin = saveAdmin("admin-editor@test.com");
        Editor editor = (Editor) userService.save(Editor.builder()
                .email("editor-tags@test.com")
                .password("123")
                .firstName("editor")
                .lastName("user")
                .createdBy(admin)
                .build());

        mockMvc.perform(get("/api/v1/tags")
                        .contextPath("/api/v1")
                        .with(authentication(authenticationFor(editor))))
                .andExpect(status().isForbidden());
    }

    @AfterEach
    void tearDown() {
        resetService.resetAll();
    }

    private Admin saveAdmin(String email) {
        return (Admin) userService.save(Admin.builder()
                .email(email)
                .password("123")
                .firstName("admin")
                .lastName("user")
                .build());
    }

    private Authentication authenticationFor(User user) {
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
