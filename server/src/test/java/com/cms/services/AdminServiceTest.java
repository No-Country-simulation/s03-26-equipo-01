package com.cms.services;

import com.cms.model.testimonial.Category;
import com.cms.model.testimonial.Tag;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
import com.cms.model.user.impl.admin.AdminResource;
import com.cms.model.user.impl.admin.ApiKey;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ResetService resetService;

    private Admin admin;
    private Admin otroAdmin;
    private Editor editor;
    private Editor editor2;
    private Category category1;
    private Category category2;
    private Category categoryDeOtroAdmin;

    @Autowired
    private TagService tagService;

    private Tag tag1;
    private Tag tag2;
    private Tag tagDeOtroAdmin;

    @BeforeEach
    public void setup(){
        admin = Admin.builder()
                .email("12312@gmail.com")
                .password("123")
                .firstName("tomas")
                .lastName("kumar")
                .build();
        admin = (Admin) userService.save(admin);

        otroAdmin = Admin.builder()
                .email("otro@gmail.com")
                .password("123")
                .firstName("otro")
                .lastName("admin")
                .build();
        otroAdmin = (Admin) userService.save(otroAdmin);

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
        editor  = (Editor) userService.save(editor);
        editor2 = (Editor) userService.save(editor2);

        category1 = categoryService.create(
                Category.builder().name("Tech").slug("tech").description("Tecnología").build(),
                admin.getId()
        );
        category2 = categoryService.create(
                Category.builder().name("Marketing").slug("marketing").description("Marketing").build(),
                admin.getId()
        );
        categoryDeOtroAdmin = categoryService.create(
                Category.builder().name("Ajena").slug("ajena").description("De otro admin").build(),
                otroAdmin.getId()
        );

        tag1 = tagService.create(Tag.builder().name("backend").build(), admin.getId());
        tag2 = tagService.create(Tag.builder().name("frontend").build(), admin.getId());
        tagDeOtroAdmin = tagService.create(Tag.builder().name("devops").build(), otroAdmin.getId());
    }

    @Test
    public void getResourceAdmin(){
        AdminResource adminResource = adminService.getResource(admin.getId());

        assertTrue(adminResource.getUsers().contains(editor));
        assertTrue(adminResource.getUsers().contains(editor2));

        assertTrue(adminResource.getCategories().contains(category1));
        assertTrue(adminResource.getCategories().contains(category2));
        assertFalse(adminResource.getCategories().contains(categoryDeOtroAdmin));

        assertTrue(adminResource.getTags().contains(tag1));
        assertTrue(adminResource.getTags().contains(tag2));
        assertFalse(adminResource.getTags().contains(tagDeOtroAdmin));
    }

    @Test
    public void save_shouldGenerateApiKeyAndAssignItToAdmin() {
        Admin savedAdmin = adminService.save(admin);

        ApiKey apiKey = savedAdmin.getApiKey();

        assertNotNull(apiKey);
        assertNotNull(apiKey.getKeyHash());
        assertTrue(apiKey.getKeyHash().startsWith("vza_"));
        assertNotNull(apiKey.getPrefix());
        assertEquals(apiKey.getKeyHash().substring(0, 12), apiKey.getPrefix());
        assertTrue(apiKey.isActive());
        assertEquals(savedAdmin, apiKey.getAdmin());
    }


    @AfterEach
    public void tearDown(){
        resetService.resetAll();
    }
}