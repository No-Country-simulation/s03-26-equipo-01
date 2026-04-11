package com.cms.services;

import com.cms.exception.business.BusinessException;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Category;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.testimonial.state.impl.PendingState;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
import com.cms.model.user.impl.admin.AdminResource;
import com.cms.model.user.impl.admin.ApiKey;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ResetService resetService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TestimonialService testimonialService;

    @Autowired
    private EmbedService embedService;

    private Admin admin;
    private Admin otroAdmin;
    private Editor editor;
    private Editor editor2;
    private Category category1;
    private Category category2;
    private Category categoryDeOtroAdmin;


    private Tag tag1;
    private Tag tag2;
    private Tag tagDeOtroAdmin;

    private Embed embed;
    private Category category;
    private List<Long> tagIds;
    private Testimonial testimonial;

    @BeforeEach
    public void setup() {
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
        editor = (Editor) userService.save(editor);
        admin.getEditors().add(editor);

        editor2 = Editor.builder()
                .email("tm123@gmail.com")
                .password("123")
                .firstName("otro")
                .lastName("usuario")
                .createdBy(admin)
                .build();
        editor2 = (Editor) userService.save(editor2);
        admin.getEditors().add(editor2);

        category1 = categoryService.create(
                Category.builder().name("Tech").slug("tech").description("Tecnología").build(),
                admin.getId()
        );
        admin.getCategories().add(category1);

        category2 = categoryService.create(
                Category.builder().name("Marketing").slug("marketing").description("Marketing").build(),
                admin.getId()
        );
        admin.getCategories().add(category2);

        categoryDeOtroAdmin = categoryService.create(
                Category.builder().name("Ajena").slug("ajena").description("De otro admin").build(),
                otroAdmin.getId()
        );

        tag1 = tagService.create(Tag.builder().name("backend").build(), admin.getId());
        admin.getTags().add(tag1);

        tag2 = tagService.create(Tag.builder().name("frontend").build(), admin.getId());
        admin.getTags().add(tag2);

        tagDeOtroAdmin = tagService.create(Tag.builder().name("devops").build(), otroAdmin.getId());

        embed = embedService.registerEmbed(admin.getId(), new Embed());
        category = categoryService.create(
                Category.builder().name("Test Category").slug("test-category").description("Category for tests").build(),
                admin.getId()
        );

        Tag tTag1 = tagService.create(Tag.builder().name("java").build(), admin.getId());
        Tag tTag2 = tagService.create(Tag.builder().name("spring").build(), admin.getId());
        tagIds = List.of(tTag1.getId(), tTag2.getId());

        testimonial = Testimonial.builder()
                .testimonial("Excelente servicio")
                .rating(5)
                .email("user@test.com")
                .build();
    }

    @Test
    public void getResourceAdmin() {
        AdminResource adminResource = adminService.getResource(admin.getId());

        assertNotNull(adminResource.getUsers());
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


    @Test
    public void advanceByAdminFromDraftThrowsBusinessException() {
        Testimonial saved = testimonialService.save(
                testimonial, embed.getId(), null,
                "https://www.youtube.com/watch?v=KhXTwEypI6c",tagIds
        );

        assertThrows(BusinessException.class, () ->
                testimonialService.advanceByAdmin(saved.getId())
        );
    }

    @Test
    public void advanceByAdminFromApprovedToPublished() {
        Testimonial saved = testimonialService.save(
                testimonial, embed.getId(), null,
                "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds
        );

        testimonialService.advanceByEditor(saved.getId());

        Testimonial recovered = testimonialService.advanceByAdmin(saved.getId());
        assertEquals(StateTestimonial.APPROVED, recovered.getState());

        recovered = testimonialService.advanceByAdmin(recovered.getId());
        assertEquals(StateTestimonial.PUBLISHED, recovered.getState());
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}