package com.cms.controller;

import com.cms.controller.dto.auth.AuthRequestDTO;
import com.cms.controller.dto.auth.AuthResponseDTO;
import com.cms.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthControllerREST {

    private final AuthService authService;

    public AuthControllerREST(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {

        AuthResponseDTO response = authService.authUser(authRequestDTO.aModelo());
        return ResponseEntity.ok(response);
    }
}
