package com.cms.services;

import com.cms.exception.business.BusinessException;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
import com.cms.persistence.sql.EditorSQLDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class EditorServiceTest {

    @Autowired private EditorService editorService;
    @Autowired private UserService userService;
    @Autowired private TestimonialService testimonialService;
    @Autowired private TagService tagService;
    @Autowired private EditorSQLDAO editorSQLDAO;
    @Autowired private ResetService resetService;

    private Admin admin;
    private Editor editor;
    private Testimonial testimonial;
    private List<Long> tagIds;

    @BeforeEach
    public void setUp() {
        admin = (Admin) userService.save(Admin.builder()
                .email("admin@test.com")
                .password("123")
                .firstName("Admin")
                .lastName("User")
                .build());

        editor = (Editor) userService.save(Editor.builder()
                .email("editor@test.com")
                .password("123")
                .firstName("Editor")
                .lastName("User")
                .createdBy(admin)
                .build());

        Tag tag1 = tagService.create(Tag.builder().name("backend").build(), admin.getId());
        Tag tag2 = tagService.create(Tag.builder().name("java").build(), admin.getId());
        tagIds = List.of(tag1.getId(), tag2.getId());

        testimonial = testimonialService.save(
                Testimonial.builder()
                        .testimonial("Excelente servicio")
                        .rating(5)
                        .email("user@test.com")
                        .state(StateTestimonial.DRAFT)
                        .build(),
                admin, null,
                "https://www.youtube.com/watch?v=KhXTwEypI6c",
                tagIds
        );
    }

    @Test
    public void asocTestimonial_shouldAddTestimonialToDrafts() {
        editorService.asocTestimonial(testimonial.getId(), editor.getId());

        Editor editorRecovered = editorSQLDAO.findById(editor.getId()).orElseThrow();

        assertTrue(editorRecovered.isContains(testimonial));
    }

    @Test
    public void advanceByEditor_shouldMoveToPendingAndRemoveFromDrafts() {
        assertThrows(BusinessException.class, () -> editorService.advanceByEditor(testimonial.getId(), editor.getId()));

        editorService.asocTestimonial(testimonial.getId(), editor.getId());

        Testimonial advanced = editorService.advanceByEditor(testimonial.getId(), editor.getId());

        assertEquals(StateTestimonial.PENDING, advanced.getState());

        Editor editorRecovered = editorSQLDAO.findById(editor.getId()).orElseThrow();
        assertFalse(editorRecovered.isContains(testimonial));

        assertThrows(BusinessException.class, () -> editorService.advanceByEditor(testimonial.getId(), editor.getId()));
    }

    @Test
    public void getTestimonialsToBank_shouldReturnDraftTestimonialsFromAdminOfEditor() {
        testimonialService.save(
                Testimonial.builder()
                        .testimonial("Borrador 2").rating(4).email("draft2@test.com")
                        .state(StateTestimonial.DRAFT).build(),
                admin, null, "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds
        );

        testimonialService.save(
                Testimonial.builder()
                        .testimonial("Publicado").rating(5).email("pub@test.com")
                        .state(StateTestimonial.PUBLISHED).build(),
                admin, null, "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds
        );

        Admin otherAdmin = (Admin) userService.save(Admin.builder()
                .email("other@test.com").password("123")
                .firstName("Other").lastName("Admin").build());

        testimonialService.save(
                Testimonial.builder()
                        .testimonial("Borrador de otro admin").rating(3).email("other@test.com")
                        .state(StateTestimonial.DRAFT).build(),
                otherAdmin, null, "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds
        );

        Page<Testimonial> result = editorService.getTestimonialsToBank(editor.getId(), 0, 5);

        assertEquals(2, result.getTotalElements());
        assertTrue(result.getContent().stream()
                .allMatch(t -> t.getState() == StateTestimonial.DRAFT));
        assertTrue(result.getContent().stream()
                .allMatch(t -> t.getAdmin().getId().equals(admin.getId())));
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}