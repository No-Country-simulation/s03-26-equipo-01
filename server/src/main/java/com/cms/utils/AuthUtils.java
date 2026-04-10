package com.cms.utils;

import org.springframework.security.core.Authentication;

public interface AuthUtils {
    public Long getUserId(Authentication authentication);

    public Long getUserId(String token);

    String getRole(Authentication auth);
}
