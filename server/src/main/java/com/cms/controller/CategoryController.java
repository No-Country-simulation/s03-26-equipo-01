package com.cms.controller;

import com.cms.controller.annotations.AdminEditorEndpoint;
import com.cms.controller.annotations.AdminEndpoint;
import com.cms.controller.annotations.EditorEndpoint;
import com.cms.controller.dto.category.CreateCategoryDto;
import com.cms.controller.dto.category.CategoryResponseDto;
import com.cms.controller.dto.category.UpdateCategoryDto;
import com.cms.model.testimonial.Category;
import com.cms.services.CategoryService;
import com.cms.utils.AuthUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@Tag(name = "Categorías", description = "Operaciones relacionadas con la gestión de categorías en el CMS")
public class CategoryController {

    private final AuthUtils authUtils;
    private final CategoryService categoryService;

    public CategoryController(AuthUtils authUtils, CategoryService categoryService) {
        this.authUtils = authUtils;
        this.categoryService = categoryService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva categoría", description = "Transforma el DTO y persiste una nueva categoría en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o slug duplicado")
    })
    @AdminEndpoint
    public ResponseEntity<CategoryResponseDto> create(Authentication auth, @Valid @RequestBody CreateCategoryDto createCategoryDto) {
        Long idAdmin = authUtils.getUserId(auth);
        Category createdCategory = categoryService.create(createCategoryDto.aModelo(), idAdmin);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryResponseDto.fromModel(createdCategory));
    }

    @GetMapping
    @AdminEndpoint
    @Operation(summary = "Obtener todas las categorías del administrador logueado", description = "Retorna una lista de las categorías activas pertenecientes al admin que realiza la petición.")
    @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida exitosamente")
    public ResponseEntity<List<CategoryResponseDto>> findAll(Authentication auth) {

        Long idAdmin = authUtils.getUserId(auth);

        List<CategoryResponseDto> categories = categoryService.findAll(idAdmin)
                .stream()
                .map(CategoryResponseDto::fromModel)
                .toList();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/search")
    @EditorEndpoint
    public ResponseEntity<List<CategoryResponseDto>> findByName(
            @RequestParam(required = false) String name,
            @RequestAttribute("userId") Long editorId) {
        List<Category> categories = categoryService.findByName(name, editorId);

        List<CategoryResponseDto> categoryResponseDtos = categories.stream()
                .map(CategoryResponseDto::fromModel)
                .toList();

        return ResponseEntity.ok(categoryResponseDtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una categoría por ID", description = "Retorna los detalles de una categoría específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public ResponseEntity<CategoryResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(CategoryResponseDto.fromModel(categoryService.findById(id)));
    }

    @PatchMapping("/{id}")
    @AdminEndpoint
    @Operation(summary = "Actualizar una categoría", description = "Actualiza parcialmente una categoría existente. Requiere privilegios de ADMIN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o slug duplicado"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
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
    @AdminEndpoint
    @Operation(summary = "Eliminar una categoría", description = "Realiza un borrado lógico de una categoría. Requiere privilegios de ADMIN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoría eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}