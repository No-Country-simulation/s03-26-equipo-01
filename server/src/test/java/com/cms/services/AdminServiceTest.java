package com.cms.services;

import com.cms.exception.business.BusinessException;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Category;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
import com.cms.model.user.impl.admin.AdminResource;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class AdminServiceTest {

    private final AdminService adminService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ResetService resetService;
    private final TagService tagService;
    private final TestimonialService testimonialService;
    private final EmbedService embedService;

    private Admin admin;
    private Admin otherAdmin;
    private Editor editor;
    private Editor secondEditor;
    private Category firstCategory;
    private Category secondCategory;
    private Category otherAdminCategory;
    private Tag firstTag;
    private Tag secondTag;
    private Tag otherAdminTag;
    private Embed embed;
    private Category testimonialCategory;
    private List<Long> tagIds;
    private Testimonial testimonial;

    public AdminServiceTest(
            AdminService adminService,
            UserService userService,
            CategoryService categoryService,
            ResetService resetService,
            TagService tagService,
            TestimonialService testimonialService,
            EmbedService embedService
    ) {
        this.adminService = adminService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.resetService = resetService;
        this.tagService = tagService;
        this.testimonialService = testimonialService;
        this.embedService = embedService;
    }

    @BeforeEach
    public void setUp() {
        admin = Admin.builder()
                .email("admin.one@test.com")
                .password("123")
                .firstName("Thomas")
                .lastName("Kumar")
                .build();
        admin = (Admin) userService.save(admin);

        otherAdmin = Admin.builder()
                .email("admin.two@test.com")
                .password("123")
                .firstName("Other")
                .lastName("Admin")
                .build();
        otherAdmin = (Admin) userService.save(otherAdmin);

        editor = Editor.builder()
                .email("editor.one@test.com")
                .password("123")
                .firstName("Thomas")
                .lastName("Editor")
                .createdBy(admin)
                .build();
        editor = (Editor) userService.save(editor);
        admin.getEditors().add(editor);

        secondEditor = Editor.builder()
                .email("editor.two@test.com")
                .password("123")
                .firstName("Second")
                .lastName("Editor")
                .createdBy(admin)
                .build();
        secondEditor = (Editor) userService.save(secondEditor);
        admin.getEditors().add(secondEditor);

        firstCategory = categoryService.create(
                Category.builder().name("Tech").slug("tech").description("Technology").build(),
                admin.getId()
        );
        admin.getCategories().add(firstCategory);

        secondCategory = categoryService.create(
                Category.builder().name("Marketing").slug("marketing").description("Marketing").build(),
                admin.getId()
        );
        admin.getCategories().add(secondCategory);

        otherAdminCategory = categoryService.create(
                Category.builder().name("External").slug("external").description("Owned by another admin").build(),
                otherAdmin.getId()
        );

        firstTag = tagService.create(Tag.builder().name("backend").build(), admin.getId());
        admin.getTags().add(firstTag);

        secondTag = tagService.create(Tag.builder().name("frontend").build(), admin.getId());
        admin.getTags().add(secondTag);

        otherAdminTag = tagService.create(Tag.builder().name("devops").build(), otherAdmin.getId());

        embed = embedService.registerEmbed(admin.getId(), new Embed());
        testimonialCategory = categoryService.create(
                Category.builder().name("Test Category").slug("test-category").description("Category for tests").build(),
                admin.getId()
        );

        Tag testimonialTagOne = tagService.create(Tag.builder().name("java").build(), admin.getId());
        Tag testimonialTagTwo = tagService.create(Tag.builder().name("spring").build(), admin.getId());
        tagIds = List.of(testimonialTagOne.getId(), testimonialTagTwo.getId());

        testimonial = Testimonial.builder()
                .testimonial("Excellent service")
                .rating(5)
                .email("user@test.com")
                .category(testimonialCategory)
                .build();
    }

    @Test
    public void shouldGetAdminResource() {
        AdminResource adminResource = adminService.getResource(admin.getId());

        assertNotNull(adminResource.getUsers());
        assertTrue(adminResource.getUsers().contains(editor));
        assertTrue(adminResource.getUsers().contains(secondEditor));

        assertTrue(adminResource.getCategories().contains(firstCategory));
        assertTrue(adminResource.getCategories().contains(secondCategory));
        assertFalse(adminResource.getCategories().contains(otherAdminCategory));

        assertTrue(adminResource.getTags().contains(firstTag));
        assertTrue(adminResource.getTags().contains(secondTag));
        assertFalse(adminResource.getTags().contains(otherAdminTag));
    }

    @Test
    public void shouldThrowWhenAdminAdvancesDraftTestimonial() {
        Testimonial saved = testimonialService.save(
                testimonial,
                embed.getId(),
                null,
                "https://www.youtube.com/watch?v=KhXTwEypI6c",
                tagIds
        );

        assertThrows(BusinessException.class, () -> testimonialService.advanceByAdmin(saved.getId()));
    }

    @Test
    public void shouldAdvanceFromApprovedToPublished() {
        Testimonial saved = testimonialService.save(
                testimonial,
                embed.getId(),
                null,
                "https://www.youtube.com/watch?v=KhXTwEypI6c",
                tagIds
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
