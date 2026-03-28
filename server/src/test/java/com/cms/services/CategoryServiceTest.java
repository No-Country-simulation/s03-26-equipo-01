package com.cms.services;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.Category;
import com.cms.persistence.repository.sql.CategorySQLDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class CategoryServiceTest {

    private final CategoryService categoryService;
    private final CategorySQLDAO categorySQLDAO;
    private final ResetService resetService;

    private Category defaultCategory;

    CategoryServiceTest(
            CategoryService categoryService,
            CategorySQLDAO categorySQLDAO,
            ResetService resetService
    ) {
        this.categoryService = categoryService;
        this.categorySQLDAO = categorySQLDAO;
        this.resetService = resetService;
    }

    @BeforeEach
    void setUp() {
        Category categoryToCreate = Category.builder()
                .name("Common Category")
                .slug("common-category")
                .description("Common description for tests")
                .build();

        defaultCategory = categoryService.create(categoryToCreate);
    }

    @Test
    void createTest() {
        Category categoryToCreate = Category.builder()
                .name("Technology")
                .slug("technology")
                .description("Technology testimonials")
                .build();

        Category createdCategory = categoryService.create(categoryToCreate);

        assertNotNull(createdCategory.getId());
        assertEquals("Technology", createdCategory.getName());
        assertEquals("technology", createdCategory.getSlug());
        assertEquals("Technology testimonials", createdCategory.getDescription());
        assertFalse(createdCategory.getDeleted());
        assertNotNull(createdCategory.getCreatedAt());
        assertNotNull(createdCategory.getUpdatedAt());

        Category persistedCategory = categoryService.findById(createdCategory.getId());
        assertNotNull(persistedCategory);
        assertEquals("technology", persistedCategory.getSlug());
    }

    @Test
    void findAllTest() {
        int initialSize = categoryService.findAll().size();

        categoryService.create(Category.builder().name("Marketing").slug("marketing").description("Marketing testimonials").build());
        categoryService.create(Category.builder().name("Sales").slug("sales").description("Sales testimonials").build());

        assertEquals(initialSize + 2, categoryService.findAll().size());
    }

    @Test
    void findByIdTest() {
        Category recoveredCategory = categoryService.findById(defaultCategory.getId());

        assertEquals(defaultCategory.getId(), recoveredCategory.getId());
        assertEquals("Common Category", recoveredCategory.getName());
    }

    @Test
    void updateTest() {
        Category updateData = Category.builder().name("Updated").description("Updated description").build();

        Category updatedCategory = categoryService.update(defaultCategory.getId(), updateData);

        assertEquals(defaultCategory.getId(), updatedCategory.getId());
        assertEquals("Updated", updatedCategory.getName());
        assertEquals("common-category", updatedCategory.getSlug());
        assertEquals("Updated description", updatedCategory.getDescription());
    }

    @Test
    void findAllShouldExcludeDeletedCategories() {
        categoryService.deleteById(defaultCategory.getId());

        assertTrue(categoryService.findAll().stream().noneMatch(category -> category.getId().equals(defaultCategory.getId())));
    }

    @Test
    void deleteByIdShouldSoftDeleteCategory() {
        categoryService.deleteById(defaultCategory.getId());

        Category deletedCategory = categorySQLDAO.findById(defaultCategory.getId()).orElseThrow();

        assertTrue(deletedCategory.getDeleted());
        assertEquals(defaultCategory.getId(), deletedCategory.getId());
        assertFalse(categoryService.findAll().stream().anyMatch(category -> category.getId().equals(defaultCategory.getId())));
        assertThrows(EntityNotFoundException.class, () -> categoryService.findById(defaultCategory.getId()));
    }

    @Test
    void createCategoryWithDuplicateSlugShouldThrowException() {
        Category duplicateCategory = Category.builder()
                .name("Duplicate")
                .slug("common-category")
                .description("Duplicate desc")
                .build();

        assertThrows(DataIntegrityViolationException.class, () -> categoryService.create(duplicateCategory));
    }

    @AfterEach
    void tearDown() {
        resetService.resetAll();
    }
}