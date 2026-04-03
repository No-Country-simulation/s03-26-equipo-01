package com.cms.services;

import com.cms.model.user.User;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
import com.cms.model.user.impl.admin.AdminResource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResetService resetService;

    private Admin admin;

    private Editor editor;

    private Editor editor2;


    @BeforeEach
    public void setup(){
        admin = Admin.builder()
                .email("12312@gmail.com")
                .password("123")
                .firstName("tomas")
                .lastName("kumar")
                .build();

        admin = (Admin)  userService.save(admin);

        editor = Editor.builder()
                .email("tm@gmail.com")
                .password("123")
                .firstName("tomas")
                .lastName("kumar")
                .createdBy(admin)
                .build();

        editor2 = Editor.builder()
                .email("tm123@gmail.com")
                .password("123")
                .firstName("otro")
                .lastName("usuario")
                .createdBy(admin)
                .build();

        editor = (Editor)  userService.save(editor);
        editor2 = (Editor)  userService.save(editor2);

    }

    @Test
    public void getResourceAdmin(){
        AdminResource adminResource = adminService.getResource(admin.getId());

        assertTrue(adminResource.getUsers().contains(editor));
        assertTrue(adminResource.getUsers().contains(editor2));

    }

    @AfterEach
    public void tearDown(){
        resetService.resetAll();
    }

}
