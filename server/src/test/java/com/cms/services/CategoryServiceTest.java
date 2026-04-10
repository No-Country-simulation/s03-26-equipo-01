package com.cms.services;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.testimonial.Category;
import com.cms.model.user.impl.admin.Admin;
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
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class CategoryServiceTest {

    private final CategoryService categoryService;
    private final UserService userService;
    private final ResetService resetService;

    private Category defaultCategory;
    private Admin primaryAdmin;

    CategoryServiceTest(CategoryService categoryService, UserService userService, ResetService resetService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.resetService = resetService;
    }

    @BeforeEach
    void setUp() {
        primaryAdmin = (Admin) userService.save(Admin.builder()
                .email("primary-admin@test.com")
                .password("123")
                .firstName("Tom")
                .lastName("Kumar")
                .build());

        Category categoryToCreate = Category.builder()
                .name("Common Category")
                .slug("common-category")
                .description("Common description for tests")
                .build();

        defaultCategory = categoryService.create(categoryToCreate, primaryAdmin.getId());
    }

    @Test
    void createShouldPersistCategoryForAdmin() {
        Category categoryToCreate = Category.builder()
                .name("Technology")
                .slug("technology")
                .description("Technology testimonials")
                .build();

        Category createdCategory = categoryService.create(categoryToCreate, primaryAdmin.getId());

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
    void findAllShouldReturnOnlyCategoriesOfRequestedAdmin() {
        Admin secondaryAdmin = (Admin) userService.save(Admin.builder()
                .email("secondary-admin@test.com")
                .password("123")
                .firstName("Jane")
                .lastName("Doe")
                .build());

        Category additionalPrimaryCategory = categoryService.create(
                Category.builder()
                        .name("Marketing")
                        .slug("marketing")
                        .description("Marketing testimonials")
                        .build(),
                primaryAdmin.getId()
        );

        Category deletedPrimaryCategory = categoryService.create(
                Category.builder()
                        .name("Archived")
                        .slug("archived")
                        .description("Archived category")
                        .build(),
                primaryAdmin.getId()
        );

        Category secondaryCategory = categoryService.create(
                Category.builder()
                        .name("Sales")
                        .slug("sales")
                        .description("Sales testimonials")
                        .build(),
                secondaryAdmin.getId()
        );

        categoryService.deleteById(deletedPrimaryCategory.getId());

        List<Long> categoryIds = categoryService.findAll(primaryAdmin.getId())
                .stream()
                .map(Category::getId)
                .toList();

        assertEquals(2, categoryIds.size());
        assertTrue(categoryIds.contains(defaultCategory.getId()));
        assertTrue(categoryIds.contains(additionalPrimaryCategory.getId()));
        assertFalse(categoryIds.contains(deletedPrimaryCategory.getId()));
        assertFalse(categoryIds.contains(secondaryCategory.getId()));
    }

    @Test
    void findByIdShouldReturnExistingCategory() {
        Category recoveredCategory = categoryService.findById(defaultCategory.getId());

        assertEquals(defaultCategory.getId(), recoveredCategory.getId());
        assertEquals("Common Category", recoveredCategory.getName());
    }

    @Test
    void updateShouldModifyExistingCategory() {
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

        assertTrue(categoryService.findAll(primaryAdmin.getId()).stream()
                .noneMatch(category -> category.getId().equals(defaultCategory.getId())));
    }

    @Test
    void deleteByIdShouldSoftDeleteCategory() {
        categoryService.deleteById(defaultCategory.getId());

        assertThrows(EntityNotFoundException.class, () -> categoryService.findById(defaultCategory.getId()));
    }


    @AfterEach
    void tearDown() {
        resetService.resetAll();
    }
}
