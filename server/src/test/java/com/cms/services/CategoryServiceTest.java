package com.cms.services;

import com.cms.controller.dtos.CreateCategoryDto;
import com.cms.controller.dtos.UpdateCategoryDto;
import com.cms.exception.business.impl.CategoryNotFoundException;
import com.cms.model.Category;
import com.cms.persistence.repository.CategoryRepository;
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
class CategoryServiceTest {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final ResetService resetService;

    CategoryServiceTest(
            CategoryService categoryService,
            CategoryRepository categoryRepository,
            ResetService resetService
    ) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.resetService = resetService;
    }

    @BeforeEach
    void setUp() {
        resetService.resetAll();
    }

    @Test
    void createTest() {
        Category createdCategory = categoryService.create(
                new CreateCategoryDto("Technology", "technology", "Technology testimonials")
        );

        assertNotNull(createdCategory.getId());
        assertEquals("Technology", createdCategory.getName());
        assertEquals("technology", createdCategory.getSlug());
        assertEquals("Technology testimonials", createdCategory.getDescription());
        assertFalse(createdCategory.getDeleted());
        assertNotNull(createdCategory.getCreatedAt());
        assertNotNull(createdCategory.getUpdatedAt());
    }

    @Test
    void findAllTest() {
        int initialSize = categoryService.findAll().size();

        categoryService.create(new CreateCategoryDto("Marketing", "marketing", "Marketing testimonials"));
        categoryService.create(new CreateCategoryDto("Sales", "sales", "Sales testimonials"));

        assertEquals(initialSize + 2, categoryService.findAll().size());
    }

    @Test
    void findByIdTest() {
        Category createdCategory = categoryService.create(
                new CreateCategoryDto("Operations", "operations", "Operations testimonials")
        );

        Category recoveredCategory = categoryService.findById(createdCategory.getId());

        assertEquals(createdCategory.getId(), recoveredCategory.getId());
        assertEquals("Operations", recoveredCategory.getName());
    }

    @Test
    void updateTest() {
        Category createdCategory = categoryService.create(
                new CreateCategoryDto("Original", "original", "Original description")
        );

        Category updatedCategory = categoryService.update(
                createdCategory.getId(),
                new UpdateCategoryDto("Updated", null, "Updated description")
        );

        assertEquals(createdCategory.getId(), updatedCategory.getId());
        assertEquals("Updated", updatedCategory.getName());
        assertEquals("original", updatedCategory.getSlug());
        assertEquals("Updated description", updatedCategory.getDescription());
    }

    @Test
    void deleteByIdTest() {
        Category createdCategory = categoryService.create(
                new CreateCategoryDto("Removable", "removable", "Removable description")
        );

        categoryService.deleteById(createdCategory.getId());

        assertEquals(true, categoryRepository.findById(createdCategory.getId()).orElseThrow().getDeleted());
        assertFalse(categoryService.findAll().stream().anyMatch(category -> category.getId().equals(createdCategory.getId())));
        assertThrows(CategoryNotFoundException.class, () -> categoryService.findById(createdCategory.getId()));
    }

    @AfterEach
    void tearDown() {
        resetService.resetAll();
    }
}
