package com.cms.services;

import com.cms.controller.dto.TagUpdateRequestDTO;
import com.cms.exception.EntityNotFoundException;
import com.cms.exception.business.BusinessException;
import com.cms.exception.business.impl.DuplicateResourceException;
import com.cms.model.Tag;
import com.cms.persistence.sql.TagSQLDAO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class TagServiceTest {

    private final TagService tagService;
    private final TagSQLDAO tagSQLDAO;
    private final ResetService resetService;

    @Test
    void createShouldNormalizeNameAndGenerateSlug() {
        Tag createdTag = tagService.create(Tag.builder().name("  BACKEND   Java  ").build());

        assertNotNull(createdTag.getId());
        assertEquals("backend java", createdTag.getName());
        assertEquals("backend-java", createdTag.getSlug());
        assertTrue(createdTag.isActive());
        assertNotNull(createdTag.getCreatedAt());
        assertNotNull(createdTag.getUpdatedAt());
    }

    @Test
    void createShouldRejectDuplicateName() {
        tagService.create(Tag.builder().name("spring boot").build());

        assertThrows(
                DuplicateResourceException.class,
                () -> tagService.create(Tag.builder().name("  Spring   Boot ").build())
        );
    }

    @Test
    void createShouldRejectBlankName() {
        assertThrows(BusinessException.class, () -> tagService.create(Tag.builder().name("   ").build()));
    }

    @Test
    void findAllShouldReturnOnlyActiveTags() {
        Tag activeTag = tagService.create(Tag.builder().name("activo").build());
        Tag deletedTag = tagService.create(Tag.builder().name("borrado").build());

        tagService.deleteById(deletedTag.getId());

        List<Tag> tags = tagService.findAll();

        assertEquals(1, tags.size());
        assertEquals(activeTag.getId(), tags.getFirst().getId());
    }

    @Test
    void findByIdShouldReturnActiveTag() {
        Tag createdTag = tagService.create(Tag.builder().name("arquitectura").build());

        Tag recoveredTag = tagService.findById(createdTag.getId());

        assertEquals(createdTag.getId(), recoveredTag.getId());
        assertEquals("arquitectura", recoveredTag.getName());
        assertEquals("arquitectura", recoveredTag.getSlug());
    }

    @Test
    void updateShouldNormalizeAndPersistNewName() {
        Tag createdTag = tagService.create(Tag.builder().name("java").build());

        Tag updatedTag = tagService.update(createdTag.getId(), new TagUpdateRequestDTO("  Java   Avanzado "));

        assertEquals(createdTag.getId(), updatedTag.getId());
        assertEquals("java avanzado", updatedTag.getName());
        assertEquals("java-avanzado", updatedTag.getSlug());
    }

    @Test
    void updateShouldRejectDuplicateNameInAnotherTag() {
        Tag existingTag = tagService.create(Tag.builder().name("spring").build());
        Tag tagToUpdate = tagService.create(Tag.builder().name("security").build());

        assertThrows(
                DuplicateResourceException.class,
                () -> tagService.update(tagToUpdate.getId(), new TagUpdateRequestDTO(existingTag.getName()))
        );
    }

    @Test
    void deleteByIdShouldApplySoftDelete() {
        Tag createdTag = tagService.create(Tag.builder().name("ciberseguridad").build());

        tagService.deleteById(createdTag.getId());

        Tag deletedTag = tagSQLDAO.findById(createdTag.getId()).orElseThrow();

        assertFalse(deletedTag.isActive());
        assertThrows(EntityNotFoundException.class, () -> tagService.findById(createdTag.getId()));
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}