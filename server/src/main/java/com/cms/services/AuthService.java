package com.cms.services;

import com.cms.controller.dto.auth.AuthResponseDTO;
import com.cms.model.AuthResult;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthService {
    AuthResult authUser(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken);
}
