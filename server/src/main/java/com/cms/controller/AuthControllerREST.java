package com.cms.controller;

import com.cms.controller.dto.auth.AuthRequestDTO;
import com.cms.controller.dto.user.UserResponseSimpleDTO;
import com.cms.controller.exception.ErrorResponseDTO;
import com.cms.model.AuthResult;
import com.cms.model.user.User;
import com.cms.services.AuthService;
import com.cms.services.UserService;
import com.cms.utils.AuthUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación", description = "Endpoints de login y seguridad")
public class AuthControllerREST {

    private final AuthService authService;
    private final UserService userService;
    private final AuthUtils authUtils;

    public AuthControllerREST(AuthService authService, UserService userService, AuthUtils authUtils) {
        this.authService = authService;
        this.userService = userService;
        this.authUtils = authUtils;
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuario")
    @ApiResponse(
            responseCode = "200",
            description = "Login exitoso - Token en header Authorization",
            headers = @io.swagger.v3.oas.annotations.headers.Header(
                    name = "Authorization",
                    description = "Bearer token",
                    schema = @Schema(type = "string")
            )
    )
    @ApiResponse(
            responseCode = "401",
            description = "Credenciales inválidas",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
    )
    @SecurityRequirements()
    public ResponseEntity<UserResponseSimpleDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {

        AuthResult result = authService.authUser(authRequestDTO.aModelo());

        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + result.token())
                .body(UserResponseSimpleDTO.fromModel(result.user()));
    }
}
