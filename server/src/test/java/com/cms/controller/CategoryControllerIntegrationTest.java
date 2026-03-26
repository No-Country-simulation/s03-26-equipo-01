package com.cms.controller;

import com.cms.model.Category;
import com.cms.persistence.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class CategoryControllerIntegrationTest {

    private final MockMvc mockMvc;
    private final CategoryRepository categoryRepository;

    CategoryControllerIntegrationTest(MockMvc mockMvc, CategoryRepository categoryRepository) {
        this.mockMvc = mockMvc;
        this.categoryRepository = categoryRepository;
    }

    @BeforeEach
    @AfterEach
    void cleanUp() {
        categoryRepository.deleteAll();
    }

    @Test
    @WithMockUser
    void createShouldReturnCreatedCategory() throws Exception {
        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Technology",
                                  "slug": "technology",
                                  "description": "Technology content"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Technology"))
                .andExpect(jsonPath("$.slug").value("technology"))
                .andExpect(jsonPath("$.description").value("Technology content"));
    }

    @Test
    @WithMockUser
    void createShouldReturnConflictWhenSlugAlreadyExists() throws Exception {
        categoryRepository.save(Category.builder()
                .name("Existing")
                .slug("existing")
                .description("Existing description")
                .build());

        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Duplicate",
                                  "slug": "existing",
                                  "description": "Duplicate description"
                                }
                                """))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Category slug already exists: existing"));
    }

    @Test
    @WithMockUser
    void findByIdShouldReturnNotFoundWhenCategoryIsDeleted() throws Exception {
        Category category = categoryRepository.save(Category.builder()
                .name("Archived")
                .slug("archived")
                .description("Archived description")
                .deleted(true)
                .build());

        mockMvc.perform(get("/categories/{id}", category.getId()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("invalid Category #" + category.getId()));
    }

    @Test
    @WithMockUser
    void findAllShouldExcludeDeletedCategories() throws Exception {
        categoryRepository.save(Category.builder()
                .name("Visible")
                .slug("visible")
                .description("Visible description")
                .build());
        categoryRepository.save(Category.builder()
                .name("Deleted")
                .slug("deleted")
                .description("Deleted description")
                .deleted(true)
                .build());

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Visible"))
                .andExpect(jsonPath("$[0].slug").value("visible"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void patchShouldUpdateOnlyProvidedFieldsForAdmin() throws Exception {
        Category category = categoryRepository.save(Category.builder()
                .name("Original")
                .slug("original")
                .description("Original description")
                .build());

        mockMvc.perform(patch("/categories/{id}", category.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "description": "Updated description"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Original"))
                .andExpect(jsonPath("$.slug").value("original"))
                .andExpect(jsonPath("$.description").value("Updated description"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void patchShouldUpdateSlugWhenProvidedForAdmin() throws Exception {
        Category category = categoryRepository.save(Category.builder()
                .name("Original")
                .slug("original-slug")
                .description("Original description")
                .build());

        mockMvc.perform(patch("/categories/{id}", category.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "slug": "updated-slug"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Original"))
                .andExpect(jsonPath("$.slug").value("updated-slug"))
                .andExpect(jsonPath("$.description").value("Original description"));
    }

    @Test
    @WithMockUser(roles = "EDITOR")
    void patchShouldReturnForbiddenForNonAdmin() throws Exception {
        Category category = categoryRepository.save(Category.builder()
                .name("Original")
                .slug("original-editor")
                .description("Original description")
                .build());

        mockMvc.perform(patch("/categories/{id}", category.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Updated"
                                }
                                """))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteShouldSoftDeleteCategoryForAdmin() throws Exception {
        Category category = categoryRepository.save(Category.builder()
                .name("Delete me")
                .slug("delete-me")
                .description("Delete me description")
                .build());

        mockMvc.perform(delete("/categories/{id}", category.getId()))
                .andExpect(status().isNoContent());

        Category deletedCategory = categoryRepository.findById(category.getId()).orElseThrow();
        org.junit.jupiter.api.Assertions.assertTrue(deletedCategory.getDeleted());
        org.junit.jupiter.api.Assertions.assertEquals(category.getId(), deletedCategory.getId());
    }

    @Test
    @WithMockUser(roles = "EDITOR")
    void deleteShouldReturnForbiddenForNonAdmin() throws Exception {
        Category category = categoryRepository.save(Category.builder()
                .name("Protected")
                .slug("protected")
                .description("Protected description")
                .build());

        mockMvc.perform(delete("/categories/{id}", category.getId()))
                .andExpect(status().isForbidden());
    }
}
