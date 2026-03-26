package com.cms.services;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.Tag;
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

@SpringBootTest
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class TagServiceTest {

    private final TagService tagService;
    private final ResetService resetService;

    TagServiceTest(TagService tagService, ResetService resetService) {
        this.tagService = tagService;
        this.resetService = resetService;
    }

    @BeforeEach
    void setUp() {
        resetService.resetAll();
    }

    @Test
    void createTest() {
        Tag tag = Tag.builder()
                .name("Featured")
                .build();

        Tag createdTag = tagService.create(tag);

        assertNotNull(createdTag.getId());
        assertEquals("Featured", createdTag.getName());
        assertNotNull(createdTag.getCreatedAt());
        assertNotNull(createdTag.getUpdatedAt());
    }

    @Test
    void findAllTest() {
        int initialSize = tagService.findAll().size();

        tagService.create(Tag.builder().name("Product").build());
        tagService.create(Tag.builder().name("Customer").build());

        assertEquals(initialSize + 2, tagService.findAll().size());
    }

    @Test
    void findByIdTest() {
        Tag createdTag = tagService.create(
                Tag.builder()
                        .name("CaseStudy")
                        .build()
        );

        Tag recoveredTag = tagService.findById(createdTag.getId());

        assertEquals(createdTag.getId(), recoveredTag.getId());
        assertEquals("CaseStudy", recoveredTag.getName());
    }

    @Test
    void updateTest() {
        Tag createdTag = tagService.create(
                Tag.builder()
                        .name("Initial")
                        .build()
        );

        Tag updatedTag = tagService.update(
                createdTag.getId(),
                Tag.builder()
                        .name("Updated")
                        .build()
        );

        assertEquals(createdTag.getId(), updatedTag.getId());
        assertEquals("Updated", updatedTag.getName());
    }

    @Test
    void deleteByIdTest() {
        Tag createdTag = tagService.create(
                Tag.builder()
                        .name("Deprecated")
                        .build()
        );

        tagService.deleteById(createdTag.getId());

        assertFalse(tagService.findAll().stream().anyMatch(tag -> tag.getId().equals(createdTag.getId())));
        assertThrows(EntityNotFoundException.class, () -> tagService.findById(createdTag.getId()));
    }

    @AfterEach
    void tearDown() {
        resetService.resetAll();
    }
}
