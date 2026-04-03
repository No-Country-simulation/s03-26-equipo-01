package com.cms.controller.annotations;

import com.cms.controller.exception.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')") // Acá habilitamos ambos roles
@SecurityRequirement(name = "bearerAuth")
@Operation(description = "Requiere rol ADMIN o EDITOR")
@ApiResponse(
        responseCode = "401",
        description = "No autenticado",
        content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(value = """
                        {
                          "timestamp": "2026-04-03T14:20:00",
                          "status": 401,
                          "error": "UNAUTHORIZED",
                          "message": "Token requerido o inválido",
                          "path": "/tags"
                        }
                        """)
        )
)
@ApiResponse(
        responseCode = "403",
        description = "No autorizado (requiere rol ADMIN o EDITOR)",
        content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(value = """
                        {
                          "timestamp": "2026-04-03T14:20:00",
                          "status": 403,
                          "error": "FORBIDDEN",
                          "message": "No tiene permisos para realizar esta acción",
                          "path": "/tags"
                        }
                        """)
        )
)
public @interface AdminEditorEndpoint {
}