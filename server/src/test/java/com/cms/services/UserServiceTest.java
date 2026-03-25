package com.cms.services;

import com.cms.exception.EntityNotFoundException;
import com.cms.exception.business.impl.DuplicateEmailException;
import com.cms.model.user.User;
import com.cms.model.user.impl.Editor;
import com.cms.persistence.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ResetService resetService;

    private User editor;

    @BeforeEach
    public void setup(){
        editor = Editor.builder()
                .email("tm@gmail.com")
                .password("123")
                .firstName("tomas")
                .lastName("kumar")
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

        assertThrows(DuplicateEmailException.class, () -> {
            User otroEditor = Editor.builder()
                    .email("tm@gmail.com")
                    .password("123")
                    .firstName("otro")
                    .lastName("usuario")
                    .build();

            userService.save(otroEditor);
        });
    }

    @Test
    public void findUserByEmailButItWasntExitedTest() {
        assertThrows(EntityNotFoundException.class, () -> userService.findUserByMail(""));
    }


    @AfterEach
    public void tearDown(){
        resetService.resetAll();
    }
}
