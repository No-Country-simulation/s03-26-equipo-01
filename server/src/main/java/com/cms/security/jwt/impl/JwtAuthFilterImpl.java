package com.cms.security.jwt.impl;

import com.cms.security.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilterImpl extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthFilterImpl(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("OPTIONS");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        if (path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-ui")
                || path.contains("swagger")
                || path.startsWith("/auth")) {
            chain.doFilter(request, response);
            return;
        }

        String token = recuperarToken(request);

        if (token == null) {
            chain.doFilter(request, response);
            return;
        }

        try {
            autenticarSiCorresponde(token, request);
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            return;
        }

        chain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    private void setAuthenticationContext(UserDetails user, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private void autenticarSiCorresponde(String token, HttpServletRequest request) {
        String username = jwtService.extraerUsername(token);

        if (username == null) return;

        if (SecurityContextHolder.getContext().getAuthentication() != null) return;

        UserDetails user = userDetailsService.loadUserByUsername(username);

        if (!jwtService.tokenValido(token, user)) return;

        setAuthenticationContext(user, request);
    }

}