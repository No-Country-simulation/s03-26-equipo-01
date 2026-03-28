package com.cms.controller.annotations;
import com.cms.controller.exception.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
@Operation(description = "Requiere rol ADMIN")
@ApiResponse(
        responseCode = "401",
        description = "No autenticado",
        content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
)
@ApiResponse(
        responseCode = "403",
        description = "No autorizado (requiere rol ADMIN)",
        content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
)
public @interface AdminEndpoint {
}