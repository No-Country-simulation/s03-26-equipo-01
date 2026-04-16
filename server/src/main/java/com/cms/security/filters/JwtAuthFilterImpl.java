package com.cms.security.filters;

import com.cms.security.jwt.JwtService;
import com.cms.security.user.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
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
        String path = request.getServletPath();
        String method = request.getMethod();

        if (method.equalsIgnoreCase("OPTIONS")) return true;

        if (path.startsWith("/v3/api-docs") ||
                path.startsWith("/swagger-ui") ||
                path.contains("swagger") ||
                path.startsWith("/auth") ||
                path.startsWith("/error") ||
                path.startsWith("/tags/search")) {
            return true;
        }

        return path.startsWith("/testimonial") ||
                path.startsWith("/embed/published");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String token = recuperarToken(request);

        if (token != null) {
            try {
                autenticarSiCorresponde(token, request);
            } catch (ExpiredJwtException e) {
                log.warn("Token expirado: {}", e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"UNAUTHORIZED\", \"message\": \"Token expirado\"}");
                return;
            } catch (Exception e) {
                log.error("Error en JWT filter: {}", e.getMessage(), e);
                SecurityContextHolder.clearContext();
            }
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

        if (user instanceof UserDetailsImpl customUser) {
            request.setAttribute("userId", customUser.getId());
        }
    }

    private void autenticarSiCorresponde(String token, HttpServletRequest request) {
        String username = jwtService.extraerUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = userDetailsService.loadUserByUsername(username);
            if (jwtService.tokenValido(token, user)) {
                setAuthenticationContext(user, request);
            }
        }
    }
}
