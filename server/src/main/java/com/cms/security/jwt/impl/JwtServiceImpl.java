package com.cms.security.jwt.impl;

import com.cms.security.jwt.JwtService;
import com.cms.security.user.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;


@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secret;

    private Key key() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }


    public String generarToken(UserDetails user) {
        UserDetailsImpl userDetails = (UserDetailsImpl) user;
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("id", userDetails.getId())
                .claim("role", user.getAuthorities()
                        .stream()
                        .findFirst()
                        .map(GrantedAuthority::getAuthority)
                        .orElse(""))
                .setIssuedAt(new Date())
                .setExpiration(new Date(
                        System.currentTimeMillis() + 3600000
                ))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extraerUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public Long extractUserId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("id", Long.class);
    }

    public boolean tokenValido(String token, UserDetails user) {
        final String username = extraerUsername(token);
        return username.equals(user.getUsername()) && !tokenExpirado(token);
    }

    private boolean tokenExpirado(String token) {
        return extraerExpiracion(token).before(new Date());
    }

    private Date extraerExpiracion(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

}
