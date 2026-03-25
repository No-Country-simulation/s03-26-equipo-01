package com.cms.controller.dto.auth;

public record AuthResponseDTO(
        String token,
        String type,
        Long id,
        String email,
        String role
) {

}
