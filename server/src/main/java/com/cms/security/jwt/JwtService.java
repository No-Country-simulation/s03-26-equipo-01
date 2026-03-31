package com.cms.security.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generarToken(UserDetails userDetails);

    boolean tokenValido(String token, UserDetails user);

    String extraerUsername(String token);
}
