package com.example.mall.global.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");

        if (bearerToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = jwtProvider.extractToken(bearerToken);

        if (jwtToken != null && jwtProvider.validateToken(jwtToken)) {
            Long subject = jwtProvider.getSubject(jwtToken);
            Claims claims = jwtProvider.getClaims(jwtToken);

            AuthPayload payload = new AuthPayload(subject,
                Role.valueOf(claims.get("role", String.class)));

            UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(payload, null,
                    List.of(new SimpleGrantedAuthority("ROLE_" + claims.get("role"))));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
