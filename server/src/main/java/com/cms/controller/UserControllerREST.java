package com.cms.controller;

import com.cms.controller.annotations.AdminEndpoint;
import com.cms.controller.dto.user.UserResponseSimpleDTO;
import com.cms.controller.exception.ErrorResponseDTO;
import com.cms.model.user.User;
import com.cms.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "Usuario", description = "Endpoints para la gestión de usuarios")
public class UserControllerREST {

    private final UserService userService;

    public UserControllerREST(UserService userService) {
        this.userService = userService;
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
    public ResponseEntity<List<UserResponseSimpleDTO>> getAllUsers() {
        List<UserResponseSimpleDTO> response = userService.findAll()
                .stream()
                .map(UserResponseSimpleDTO::fromModel)
                .toList();

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