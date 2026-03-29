package com.cms.controller.dto.user;

import com.cms.model.user.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UserResponseSimpleDTO", description = "DTO simplificado de usuario")
public record UserResponseSimpleDTO(

        @Schema(description = "ID del usuario", example = "1")
        Long id,

        @Schema(description = "Email del usuario", example = "usuario@email.com")
        String email,

        @Schema(description = "Nombre del usuario", example = "Tomás")
        String firstName,

        @Schema(description = "Apellido del usuario", example = "Mendoza")
        String lastName

) {
    public static UserResponseSimpleDTO fromModel(User user){
        return new UserResponseSimpleDTO(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}