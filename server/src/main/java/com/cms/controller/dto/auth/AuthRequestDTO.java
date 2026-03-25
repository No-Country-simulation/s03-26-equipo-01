package com.cms.controller.dto.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record AuthRequestDTO(
        String email,
        String password
){
    public UsernamePasswordAuthenticationToken aModelo(){
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
