package com.cms.controller.dto.auth;

import jakarta.validation.constraints.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Body de login")
public record AuthRequestDTO(
        @Schema(example = "tm@gmail.com", description = "Email del usuario")
        @NotNull
        String email,

        @Schema(example = "123456", description = "Contraseña")
        @NotNull
        String password
){
    public UsernamePasswordAuthenticationToken aModelo(){
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
