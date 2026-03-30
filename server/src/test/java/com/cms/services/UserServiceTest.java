package com.cms.services;

import com.cms.exception.EntityNotFoundException;
import com.cms.exception.business.impl.DuplicateEmailException;
import com.cms.model.user.User;
import com.cms.model.user.impl.Editor;
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

    @BeforeEach
    public void setup(){
        editor = Editor.builder()
                .email("tm@gmail.com")
                .password("123")
                .firstName("tomas")
                .lastName("kumar")
                .build();

        otroEditor = Editor.builder()
                .email("tm123@gmail.com")
                .password("123")
                .firstName("otro")
                .lastName("usuario")
                .build();

    }

    @Test
    public void saveUserAndGetTest() {
        User editorSaved = userService.save(editor);

        User editorRecovered = userService.findUserByMail(editorSaved.getEmail());

        assertEquals(editor.getEmail(), editorRecovered.getEmail());
        assertEquals(editor.getPassword(), editorRecovered.getPassword());
        assertEquals(editor.getFirstName(), editorRecovered.getFirstName());
        assertEquals(editor.getLastName(), editorRecovered.getLastName());

        otroEditor = Editor.builder()
                .email("tm@gmail.com")
                .password("123")
                .firstName("otro")
                .lastName("usuario")
                .build();

        assertThrows(DuplicateEmailException.class, () -> {
            userService.save(otroEditor);
        });
    }

    @Test
    public void findUserByEmailButItWasntExitedTest() {
        assertThrows(EntityNotFoundException.class, () -> userService.findUserByMail(""));
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

    @AfterEach
    public void tearDown(){
        resetService.resetAll();
    }
}
