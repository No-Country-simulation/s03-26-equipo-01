package com.cms.controller.exception;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de error estándar")
public record ErrorResponseDTO(

        @Schema(
                description = "Fecha y hora del error",
                example = "2026-03-25T14:20:00"
        )
        LocalDateTime timestamp,

        @Schema(
                description = "Código HTTP",
                example = "401"
        )
        int status,

        @Schema(
                description = "Tipo de error",
                example = "UNAUTHORIZED"
        )
        String error,

        @Schema(
                description = "Mensaje descriptivo",
                example = "Credenciales inválidas"
        )
        String message,

        @Schema(
                description = "Ruta del endpoint",
                example = "/auth/login"
        )
        String path

) {
    public static ErrorResponseDTO of(int status, String error, String message, String path) {
        return new ErrorResponseDTO(
                LocalDateTime.now(),
                status,
                error,
                message,
                path
        );
    }
}