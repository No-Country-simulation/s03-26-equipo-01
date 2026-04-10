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
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

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
    private Editor firstEditor;
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
        admin = (Admin) userService.save(Admin.builder()
                .email("admin@test.com")
                .password("123")
                .firstName("Thomas")
                .lastName("Kumar")
                .build());

        otherAdmin = (Admin) userService.save(Admin.builder()
                .email("other-admin@test.com")
                .password("123")
                .firstName("Other")
                .lastName("Admin")
                .build());

        firstEditor = (Editor) userService.save(Editor.builder()
                .email("editor-one@test.com")
                .password("123")
                .firstName("Tom")
                .lastName("Editor")
                .createdBy(admin)
                .build());
        admin.getEditors().add(firstEditor);

        secondEditor = (Editor) userService.save(Editor.builder()
                .email("editor-two@test.com")
                .password("123")
                .firstName("Jane")
                .lastName("Editor")
                .createdBy(admin)
                .build());
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
                Category.builder().name("External").slug("external").description("Other admin category").build(),
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

        Tag javaTag = tagService.create(Tag.builder().name("java").build(), admin.getId());
        Tag springTag = tagService.create(Tag.builder().name("spring").build(), admin.getId());
        tagIds = List.of(javaTag.getId(), springTag.getId());

        testimonial = Testimonial.builder()
                .testimonial("Excellent service")
                .witness("John Doe")
                .rating(5)
                .email("user@test.com")
                .category(testimonialCategory)
                .build();
    }

    @Test
    public void getAdminResource() {
        AdminResource adminResource = adminService.getResource(admin.getId());

        assertNotNull(adminResource.getUsers());
        assertTrue(adminResource.getUsers().contains(firstEditor));
        assertTrue(adminResource.getUsers().contains(secondEditor));

        assertTrue(adminResource.getCategories().contains(firstCategory));
        assertTrue(adminResource.getCategories().contains(secondCategory));
        assertFalse(adminResource.getCategories().contains(otherAdminCategory));

        assertTrue(adminResource.getTags().contains(firstTag));
        assertTrue(adminResource.getTags().contains(secondTag));
        assertFalse(adminResource.getTags().contains(otherAdminTag));
    }

    @Test
    public void advanceByAdminFromDraftThrowsBusinessException() {
        Testimonial saved = testimonialService.save(
                testimonial,
                embed.getId(),
                null,
                "",
                tagIds
        );

        assertThrows(BusinessException.class, () ->
                testimonialService.advanceByAdmin(saved.getId())
        );
    }

    @Test
    public void advanceByAdminFromApprovedToPublished() {
        Testimonial saved = testimonialService.save(
                testimonial,
                embed.getId(),
                null,
                "",
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
