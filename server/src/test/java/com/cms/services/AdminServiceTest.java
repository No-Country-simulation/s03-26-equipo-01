package com.cms.services;

import com.cms.exception.EntityNotFoundException;
import com.cms.exception.business.BusinessException;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Category;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
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
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

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
                testimonial,admin, null,
                "https://www.youtube.com/watch?v=KhXTwEypI6c",tagIds
        );

        assertThrows(BusinessException.class, () ->
                testimonialService.advanceByAdmin(saved.getId())
        );
    }

    @Test
    public void advanceByAdminFromApprovedToPublished() {
        Testimonial saved = testimonialService.save(
                testimonial,admin, null,
                "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds
        );

        testimonialService.advanceByEditor(saved.getId());

        assertThrows(BusinessException.class, () -> testimonialService.advanceByAdmin(saved.getId()));

        saved.setCategory(category2);

        testimonialService.update(saved);

        Testimonial recovered = testimonialService.advanceByAdmin(saved.getId());

        assertEquals(StateTestimonial.APPROVED, recovered.getState());

        recovered = testimonialService.advanceByAdmin(recovered.getId());
        assertEquals(StateTestimonial.PUBLISHED, recovered.getState());
    }

    @Test
    public void createEditor_shouldCreateEditorAndAssignItToAdmin() {
        Editor newEditor = Editor.builder()
                .email("neweditor@gmail.com")
                .password("securepass")
                .firstName("John")
                .lastName("Doe")
                .build();

        Editor created = adminService.createEditor(newEditor, admin.getId());

        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals("neweditor@gmail.com", created.getEmail());
        assertEquals("John", created.getFirstName());
        assertEquals("Doe", created.getLastName());
        assertEquals(admin, created.getCreatedBy());
    }

    @Test
    public void createEditor_shouldBeEnabledByDefault() {
        Editor newEditor = Editor.builder()
                .email("enabled@gmail.com")
                .password("pass")
                .firstName("Ana")
                .lastName("Lopez")
                .build();

        Editor created = adminService.createEditor(newEditor, admin.getId());

        assertTrue(created.isEnabled());
    }

    @Test
    public void createEditor_withNonExistentAdmin_shouldThrowEntityNotFoundException() {
        Editor newEditor = Editor.builder()
                .email("ghost@gmail.com")
                .password("pass")
                .firstName("Ghost")
                .lastName("User")
                .build();

        long nonExistentAdminId = -999L;

        assertThrows(EntityNotFoundException.class, () ->
                adminService.createEditor(newEditor, nonExistentAdminId)
        );
    }

    @Test
    public void createEditor_shouldHaveEditorRole() {
        Editor newEditor = Editor.builder()
                .email("role@gmail.com")
                .password("pass")
                .firstName("Role")
                .lastName("Test")
                .build();

        Editor created = adminService.createEditor(newEditor, admin.getId());

        assertEquals("EDITOR", created.getRole());
    }

    @Test
    public void createEditor_multipleEditorsUnderSameAdmin_shouldAllBeAssigned() {
        Editor first = Editor.builder()
                .email("first@gmail.com")
                .password("pass")
                .firstName("First")
                .lastName("Editor")
                .build();

        Editor second = Editor.builder()
                .email("second@gmail.com")
                .password("pass")
                .firstName("Second")
                .lastName("Editor")
                .build();

        Editor createdFirst  = adminService.createEditor(first,  admin.getId());
        Editor createdSecond = adminService.createEditor(second, admin.getId());

        assertEquals(admin, createdFirst.getCreatedBy());
        assertEquals(admin, createdSecond.getCreatedBy());
        assertNotEquals(createdFirst.getId(), createdSecond.getId());
    }

    @Test
    public void createEditor_editorCreatedByOneAdmin_shouldNotBelongToAnother() {
        Editor newEditor = Editor.builder()
                .email("isolated@gmail.com")
                .password("pass")
                .firstName("Isolated")
                .lastName("Editor")
                .build();

        Editor created = adminService.createEditor(newEditor, admin.getId());

        assertNotEquals(otroAdmin, created.getCreatedBy());
    }
    @Test
    public void findAllEditors_shouldReturnEditorsOfAdmin() {
        Page<Editor> result = adminService.findAllEditors(admin.getId(), 0, 10);

        assertNotNull(result);
        assertTrue(result.getTotalElements() >= 2);
        assertTrue(result.getContent().contains(editor));
        assertTrue(result.getContent().contains(editor2));
    }

    @Test
    public void findAllEditors_shouldNotReturnEditorsOfOtherAdmins() {
        Editor editorDeOtroAdmin = Editor.builder()
                .email("otro@editor.com")
                .password("pass")
                .firstName("Ajeno")
                .lastName("Editor")
                .createdBy(otroAdmin)
                .build();
        userService.save(editorDeOtroAdmin);

        Page<Editor> result = adminService.findAllEditors(admin.getId(), 0, 10);

        assertFalse(result.getContent().contains(editorDeOtroAdmin));
    }

    @Test
    public void findAllEditors_shouldRespectPagination() {
        Page<Editor> firstPage  = adminService.findAllEditors(admin.getId(), 0, 1);
        Page<Editor> secondPage = adminService.findAllEditors(admin.getId(), 1, 1);

        assertEquals(1, firstPage.getContent().size());
        assertEquals(1, secondPage.getContent().size());
        assertNotEquals(firstPage.getContent().get(0), secondPage.getContent().get(0));
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}