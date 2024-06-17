package com.backend.almfit.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.Key;
import java.util.*;


@Component
public class JwtTokenUtils {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final long expirationTime = 3600000 * 24; // 24 hours

    public String createJwtToken(String username, Collection<? extends GrantedAuthority> roles) throws JsonProcessingException {
        Map<String, Object> claims = new HashMap<>();
        boolean isAdmin = roles.stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
        claims.put("isAdmin", isAdmin);
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .signWith(secretKey)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .compact();
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        final Date expiration = getClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }


    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public Collection<? extends GrantedAuthority> getAuthoritiesFromToken(String token) throws IOException {
        Claims claims = getClaimsFromToken(token);
        String authorities = claims.get("authorities").toString();
        return Arrays.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                .readValue(authorities.getBytes(), SimpleGrantedAuthority[].class));
    }
}
