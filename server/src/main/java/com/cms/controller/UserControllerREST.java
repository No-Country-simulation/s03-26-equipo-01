package com.cms.controller;

import com.cms.controller.annotations.AdminEditorEndpoint;
import com.cms.controller.annotations.AdminEndpoint;
import com.cms.controller.annotations.EditorEndpoint;
import com.cms.controller.dto.tag.TagResponseDto;
import com.cms.controller.dto.user.UserResponseSimpleDTO;
import com.cms.controller.dto.utils.PageResponseDTO;
import com.cms.controller.exception.ErrorResponseDTO;
import com.cms.model.user.User;
import com.cms.services.TagService;
import com.cms.services.UserService;
import com.cms.utils.AuthUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "Usuario", description = "Endpoints para la gestión de usuarios")
public class UserControllerREST {

    private final UserService userService;
    private final AuthUtils authUtils;
    private final TagService tagService;

    public UserControllerREST(UserService userService, AuthUtils authUtils, TagService tagService) {
        this.userService = userService;
        this.authUtils = authUtils;
        this.tagService = tagService;
    }

    @GetMapping("/detail")
    @Operation(summary = "obtener los datos de un usuario autenticado")
    @ApiResponse(
           responseCode = "200",
           description = "Retorna los datos de un usuario autenticado",
           content = @Content(schema = @Schema(implementation = UserResponseSimpleDTO.class))

    )
    @ApiResponse(
            responseCode = "404",
            description = "Retorna información sobre el error del usuario no encontrado",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))

    )
    public ResponseEntity<UserResponseSimpleDTO> getUser(Authentication authentication) {
        Long idUser = authUtils.getUserId(authentication);

        User user = userService.findById(idUser);

        return ResponseEntity.ok(UserResponseSimpleDTO.fromModel(user));
    }

    @GetMapping("/{enabled}")
    @AdminEndpoint
    @Operation(summary = "Obtener usuarios por estado")
    public ResponseEntity<List<UserResponseSimpleDTO>> getUsersByEnabled(
            @Parameter(description = "Estado del usuario (true = habilitado, false = deshabilitado)", example = "true")
            @PathVariable boolean enabled) {

        List<UserResponseSimpleDTO> response = userService.findAllEnabled(enabled)
                .stream()
                .map(UserResponseSimpleDTO::fromModel)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @AdminEndpoint
    @Operation(
            summary = "Obtener todos los usuarios paginados",
            description = "Retorna una página de usuarios registrados en el sistema. Se puede navegar entre páginas usando el parámetro `page`."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Página de usuarios obtenida correctamente",
            content = @Content(schema = @Schema(implementation = PageResponseDTO.class))
    )
    public ResponseEntity<PageResponseDTO<UserResponseSimpleDTO>> getAllUsers(
            @Parameter(description = "Número de página a obtener (base 0)", example = "0")
            @RequestParam(defaultValue = "0") int page) {

        PageResponseDTO<UserResponseSimpleDTO> response = PageResponseDTO.from(
                userService.findAll(page).map(UserResponseSimpleDTO::fromModel)
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{idUser}")
    @AdminEndpoint
    @Operation(summary = "Deshabilitar usuario (soft delete)")
    @ApiResponse(responseCode = "204", description = "Usuario deshabilitado correctamente")
    @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
    )
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID del usuario", example = "1")
            @PathVariable Long idUser) {

        userService.disableUser(idUser);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/enable/{idUser}")
    @AdminEndpoint
    @Operation(summary = "Habilitar un usuario")
    @ApiResponse(responseCode = "204", description = "Usuario habilitado correctamente")
    @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
    )
    public ResponseEntity<Void> enableUser(
            @Parameter(description = "ID del usuario", example = "1")
            @PathVariable Long idUser) {

        userService.enableUser(idUser);
        return ResponseEntity.noContent().build();
    }


}