package com.cms.controller;

import com.cms.controller.dto.category.CreateCategoryDto;
import com.cms.controller.dto.category.CategoryResponseDto;
import com.cms.controller.dto.category.UpdateCategoryDto;
import com.cms.model.Category;
import com.cms.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@Tag(name = "Categories", description = "Operations related to category management in the CMS")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @Operation(summary = "Create a new category", description = "Transforms the DTO and persists a new category in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input data or duplicate slug")
    })
    public ResponseEntity<CategoryResponseDto> create(@Valid @RequestBody CreateCategoryDto createCategoryDto) {
        Category createdCategory = categoryService.create(createCategoryDto.aModelo());
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryResponseDto.fromModel(createdCategory));
    }

    @GetMapping
    @Operation(summary = "Get all active categories", description = "Retrieves a list of all categories that have not been soft-deleted.")
    @ApiResponse(responseCode = "200", description = "List of categories retrieved successfully")
    public ResponseEntity<List<CategoryResponseDto>> findAll() {
        List<CategoryResponseDto> categories = categoryService.findAll()
                .stream()
                .map(CategoryResponseDto::fromModel)
                .toList();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a category by ID", description = "Retrieves the details of a specific category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<CategoryResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(CategoryResponseDto.fromModel(categoryService.findById(id)));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a category", description = "Partially updates an existing category. Requires ADMIN privileges.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data or duplicate slug"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<CategoryResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCategoryDto updateCategoryDto
    ) {
        Category categoryToUpdate = categoryService.findById(id);
        updateCategoryDto.aplicar(categoryToUpdate);
        Category updatedCategory = categoryService.update(id, categoryToUpdate);
        return ResponseEntity.ok(CategoryResponseDto.fromModel(updatedCategory));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a category", description = "Performs a soft delete on a category. Requires ADMIN privileges.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}