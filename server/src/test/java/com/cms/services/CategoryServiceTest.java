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
        resetService.resetAll();
    }

    @Test
    void createTest() {
        Category categoryToCreate = Category.builder()
                .name("Technology")
                .slug("technology")
                .description("Technology testimonials")
                .build();

        Category createdCategory = categoryService.create(categoryToCreate);

        // Verify the object returned by the service
        assertNotNull(createdCategory.getId());
        assertEquals("Technology", createdCategory.getName());
        assertEquals("technology", createdCategory.getSlug());
        assertEquals("Technology testimonials", createdCategory.getDescription());
        assertFalse(createdCategory.getDeleted());
        assertNotNull(createdCategory.getCreatedAt());
        assertNotNull(createdCategory.getUpdatedAt());

        // Verify the object was actually persisted in the database
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
        Category categoryToCreate = Category.builder().name("Operations").slug("operations").description("Operations testimonials").build();
        Category createdCategory = categoryService.create(categoryToCreate);

        Category recoveredCategory = categoryService.findById(createdCategory.getId());

        assertEquals(createdCategory.getId(), recoveredCategory.getId());
        assertEquals("Operations", recoveredCategory.getName());
    }

    @Test
    void updateTest() {
        Category categoryToCreate = Category.builder().name("Original").slug("original").description("Original description").build();
        Category createdCategory = categoryService.create(categoryToCreate);

        Category updateData = Category.builder().name("Updated").description("Updated description").build();

        Category updatedCategory = categoryService.update(createdCategory.getId(), updateData);

        assertEquals(createdCategory.getId(), updatedCategory.getId());
        assertEquals("Updated", updatedCategory.getName());
        assertEquals("original", updatedCategory.getSlug());
        assertEquals("Updated description", updatedCategory.getDescription());
    }

    @Test
    void findAllShouldExcludeDeletedCategories() {
        Category categoryToCreate = Category.builder().name("Visible").slug("visible").description("Visible description").build();
        Category createdCategory = categoryService.create(categoryToCreate);

        categoryService.deleteById(createdCategory.getId());

        assertTrue(categoryService.findAll().stream().noneMatch(category -> category.getId().equals(createdCategory.getId())));
    }

    @Test
    void deleteByIdShouldSoftDeleteCategory() {
        Category categoryToCreate = Category.builder().name("Removable").slug("removable").description("Removable description").build();
        Category createdCategory = categoryService.create(categoryToCreate);

        categoryService.deleteById(createdCategory.getId());

        Category deletedCategory = categorySQLDAO.findById(createdCategory.getId()).orElseThrow();

        assertTrue(deletedCategory.getDeleted());
        assertEquals(createdCategory.getId(), deletedCategory.getId());
        assertFalse(categoryService.findAll().stream().anyMatch(category -> category.getId().equals(createdCategory.getId())));
        assertThrows(EntityNotFoundException.class, () -> categoryService.findById(createdCategory.getId()));
    }

    @Test
    void createCategoryWithDuplicateSlugShouldThrowException() {
        Category firstCategory = Category.builder().name("First").slug("duplicate-slug").description("Desc 1").build();
        categoryService.create(firstCategory);

        Category secondCategory = Category.builder().name("Second").slug("duplicate-slug").description("Desc 2").build();

        // Verify that the database constraint throws a DataIntegrityViolationException
        assertThrows(DataIntegrityViolationException.class, () -> categoryService.create(secondCategory));
    }

    @AfterEach
    void tearDown() {
        resetService.resetAll();
    }
}