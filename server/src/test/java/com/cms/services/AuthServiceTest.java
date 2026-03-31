package com.cms.services;


import com.cms.exception.EntityNotFoundException;
import com.cms.model.user.User;
import com.cms.model.user.impl.Admin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private ResetService resetService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User admin;

    @BeforeEach
    public void setup() {
        admin = Admin.builder()
                .email("admin@mail.com")
                .password("password123")
                .firstName("John")
                .lastName("Doe")
                .build();

        userService.save(admin);
    }

    @Test
    public void authUser_validCredentials_returnsResponseWithJwt() {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken("admin@mail.com", "password123");

        String jwt = authService.authUser(token);

        assertNotNull(jwt);
    }

    @Test
    public void authUser_invalidCredentials_throwsBadCredentialsException() {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken("admin@mail.com", "wrong-password");

        assertThrows(BadCredentialsException.class, () -> authService.authUser(token));
    }

    @Test
    public void authUser_nonExistentUser_throwsException() {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken("noexiste@mail.com", "password123");

        assertThrows(EntityNotFoundException.class, () -> authService.authUser(token));
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}