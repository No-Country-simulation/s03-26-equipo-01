package com.cms.utils.impl;

import com.cms.security.jwt.JwtService;
import com.cms.security.user.UserDetailsImpl;
import com.cms.utils.AuthUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthUtilsImpl implements AuthUtils {

    private final JwtService jwtService;

    public AuthUtilsImpl(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public Long getUserId(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails.getId();
    }

    @Override
    public Long getUserId(String token) {
        return jwtService.extractUserId(token);
    }
}