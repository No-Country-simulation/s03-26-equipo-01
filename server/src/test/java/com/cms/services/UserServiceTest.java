package com.cms.services;

import com.cms.exception.EntityNotFoundException;
import com.cms.exception.business.impl.DuplicateEmailException;
import com.cms.model.user.User;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
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
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ResetService resetService;

    private User editor;

    private User otroEditor;

    private Admin adminSaved;

    @BeforeEach
    public void setup(){
        Admin admin = Admin.builder()
                .email("12312@gmail.com")
                .password("123")
                .firstName("tomas")
                .lastName("kumar")
                .build();

        adminSaved = (Admin)  userService.save(admin);

        editor = Editor.builder()
                .email("tm@gmail.com")
                .password("123")
                .firstName("tomas")
                .lastName("kumar")
                .createdBy(adminSaved)
                .build();

        otroEditor = Editor.builder()
                .email("tm123@gmail.com")
                .password("123")
                .firstName("otro")
                .lastName("usuario")
                .createdBy(adminSaved)
                .build();

    }

    @Test
    public void saveUserAndGetTest() {
        Editor editorSaved = (Editor) userService.save(editor);

        Editor editorRecovered = (Editor) userService.findUserByMail(editorSaved.getEmail()).get();

        assertEquals(editorSaved.getEmail(), editorRecovered.getEmail());
        assertEquals(editorSaved.getPassword(), editorRecovered.getPassword());
        assertEquals(editorSaved.getFirstName(), editorRecovered.getFirstName());
        assertEquals(editorSaved.getLastName(), editorRecovered.getLastName());
        assertEquals(editorSaved.getCreatedBy().getId(), editorRecovered.getCreatedBy().getId());

        otroEditor = Editor.builder()
                .email("tm@gmail.com")
                .password("123")
                .firstName("otro")
                .lastName("usuario")
                .createdBy(adminSaved)
                .build();

        assertThrows(DuplicateEmailException.class, () -> {
            userService.save(otroEditor);
        });
    }


    @Test
    public void disableUserAndGetEnableUserTest() {
        User editorSaved = userService.save(editor);

        User editorSaved2 = userService.save(otroEditor);

        userService.disableUser(editorSaved.getId());

        List<User> users = userService.findAllEnabled(true);

        assertFalse(users.contains(editorSaved));
        assertTrue(users.contains(editorSaved2));
    }

    @Test
    public void enableUserAndGetEnableUserTest() {
        User editorSaved = userService.save(editor);
        userService.disableUser(editorSaved.getId());
        User editorSaved2 = userService.save(otroEditor);

        userService.enableUser(editorSaved.getId());
        List<User> users = userService.findAllEnabled(true);

        assertTrue(users.contains(editorSaved));
        assertTrue(users.contains(editorSaved2));
    }

    @Test
    public void findAllOrderByEnabledDescTest() {
        User editorSaved = userService.save(editor);
        User otroEditorSaved = userService.save(otroEditor);

        userService.disableUser(editorSaved.getId());

        List<User> users = userService.findAll(0).getContent();

        int indexHabilitado = users.indexOf(otroEditorSaved);
        int indexDeshabilitado = users.indexOf(editorSaved);

        assertTrue(indexHabilitado < indexDeshabilitado);
    }

    @AfterEach
    public void tearDown(){
        resetService.resetAll();
    }
}
