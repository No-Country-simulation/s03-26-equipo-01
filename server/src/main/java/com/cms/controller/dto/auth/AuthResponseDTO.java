package com.cms.controller.dto.auth;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de autenticación con JWT")
public record AuthResponseDTO(

        @Schema(
                description = "Token JWT para autenticación",
                example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
        )
        String token,

        @Schema(
                description = "Tipo de token",
                example = "Bearer"
        )
        String type,

        @Schema(
                description = "ID del usuario autenticado",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Email del usuario",
                example = "tm@gmail.com"
        )
        String email,

        @Schema(
                description = "Rol del usuario",
                example = "EDITOR"
        )
        String role

) {}
