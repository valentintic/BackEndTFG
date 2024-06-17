package com.backend.almfit.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static com.backend.almfit.auth.TokenJwtConfig.*;


@Component
public class JwtUtil {


    public String createJwtToken(UserDetails userDetails) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("authorities", "[{\"authority\":\"ROLE_USER\"}]");

        claimsMap.put("isAdmin", false);
        claimsMap.put("username", userDetails.getUsername());

        Claims claims = Jwts.claims(claimsMap);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .signWith(SECRET_KEY)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (3600000 * 24 )))
                .compact();
    }
}
