package com.example.mall.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private Key key;
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(bytes);
    }

    public String createToken(Long userId, Role role) {
        Date now = new Date();

        return Jwts.builder()
            .setSubject(String.valueOf(userId))
            .claim("role", role)
            .setExpiration(new Date(now.getTime() + expiration))
            .setIssuedAt(now)
            .signWith(key, signatureAlgorithm)
            .compact();
    }

    public String extractToken(String token) {
        if (token.startsWith(BEARER_PREFIX)) {
            return token.substring(7);
        }

        throw new IllegalArgumentException("Invalid JWT");
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public Long getSubject(String token) {
        return Long.valueOf(getClaims(token).getSubject());
    }
}
