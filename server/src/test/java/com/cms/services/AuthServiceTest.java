package com.cms.services;

import com.cms.model.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AuthServiceTest {

    @Autowired
    private UserService userService;

    private User editor;

    @BeforeEach
    public void setup(){}


    @AfterEach
    public void tearDown(){

    }
}
