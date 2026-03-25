package com.cms.services;

import com.cms.controller.dto.auth.AuthResponseDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthService {
    AuthResponseDTO authUser(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken);
}
