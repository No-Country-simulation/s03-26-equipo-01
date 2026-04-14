package com.cms.controller;

import com.cms.controller.annotations.AdminEditorEndpoint;
import com.cms.controller.annotations.AdminEndpoint;
import com.cms.controller.dto.tag.TagRequestDTO;
import com.cms.controller.dto.tag.TagRequestSearchDTO;
import com.cms.controller.dto.tag.TagResponseDto;
import com.cms.controller.dto.tag.TagUpdateRequestDTO;
import com.cms.model.testimonial.Tag;
import com.cms.services.TagService;
import com.cms.utils.AuthUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
@io.swagger.v3.oas.annotations.tags.Tag(name = "Tags", description = "Gestión de tags")
public class TagController {

    private final TagService tagService;
    private final AuthUtils authUtils;

    @Operation(summary = "Obtener todos los tags activos del admin autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tags obtenida correctamente")
    })
    @GetMapping
    @AdminEndpoint
    public ResponseEntity<List<TagResponseDto>> findAll(Authentication auth) {
        Long idAdmin = authUtils.getUserId(auth);

        List<TagResponseDto> tags = tagService.findAllByAdmin(idAdmin)
                .stream()
                .map(TagResponseDto::fromEntity)
                .toList();
        return ResponseEntity.ok(tags);
    }

    @Operation(summary = "Obtener un tag por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag encontrado",
                    content = @Content(schema = @Schema(implementation = TagResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Tag no encontrado")
    })
    @GetMapping("/{id}")
    @AdminEditorEndpoint
    public ResponseEntity<TagResponseDto> findById(
            @Parameter(description = "ID del tag", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(TagResponseDto.fromEntity(tagService.findById(id)));
    }

    @Operation(summary = "Crear un nuevo tag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tag creado correctamente",
                    content = @Content(schema = @Schema(implementation = TagResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "409", description = "Tag duplicado")
    })
    @PostMapping
    @AdminEndpoint
    public ResponseEntity<TagResponseDto> create(
            @Valid
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del tag a crear",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TagRequestDTO.class))
            )
            TagRequestDTO tagRequestDTO, Authentication authentication) {

        Long idAdmin = authUtils.getUserId(authentication);
        Tag createdTag = tagService.create(tagRequestDTO.toEntity(), idAdmin);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TagResponseDto.fromEntity(createdTag));
    }

    @Operation(summary = "Actualizar un tag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag actualizado correctamente",
                    content = @Content(schema = @Schema(implementation = TagResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Tag no encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflicto por duplicado")
    })
    @PutMapping("/{id}")
    @AdminEndpoint
    public ResponseEntity<TagResponseDto> update(
            @Parameter(description = "ID del tag", example = "1")
            @PathVariable Long id,

            @Valid
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos a actualizar del tag",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TagUpdateRequestDTO.class))
            )
            TagUpdateRequestDTO updateTagDto
    ) {
        Tag updatedTag = tagService.update(id, updateTagDto);
        return ResponseEntity.ok(TagResponseDto.fromEntity(updatedTag));
    }

    @Operation(summary = "Eliminar un tag (soft delete)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tag eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Tag no encontrado")
    })
    @DeleteMapping("/{id}")
    @AdminEndpoint
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID del tag", example = "1")
            @PathVariable Long id) {

        tagService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    @SecurityRequirements()
    public ResponseEntity<List<TagResponseDto>> findByNameTag(@RequestBody @Valid TagRequestSearchDTO request) {
        List<Tag> tags = tagService.findTagsByName(request.name(), request.idAdmin());

        List<TagResponseDto> response = tags.stream().map(TagResponseDto::fromEntity).toList();

        return ResponseEntity.ok(response);
    }

}
