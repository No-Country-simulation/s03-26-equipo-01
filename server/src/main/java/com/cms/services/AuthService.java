package com.cms.services;

import com.cms.model.user.AuthResult;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthService {
    AuthResult authUser(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken);
}
