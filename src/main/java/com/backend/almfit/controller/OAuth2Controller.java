package com.backend.almfit.controller;

import com.backend.almfit.auth.JwtTokenUtils;
import com.backend.almfit.entity.User.Usuario;
import com.backend.almfit.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/oauth2")
public class OAuth2Controller {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenUtils jwtUtil;

    @GetMapping("/redirect")
    public void handleOAuth2Redirect(@RequestParam String code, HttpServletResponse response, OAuth2AuthenticationToken authentication) throws IOException {
        log.info("OAuth2 Redirect");
        if (authentication == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"mensaje\": \"No estás autenticado\"}");
            return;
        }

        log.info("Code: " + code);
        log.info("Authentication: " + authentication);
        log.info("Principal: " + authentication.getPrincipal());

        OAuth2User oAuth2User = authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        Usuario user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        Collection<? extends GrantedAuthority> roles = user.getAuthorities();
        String token = jwtUtil.createJwtToken(user.getUsername(), roles);

        log.info("Generated Token: " + token);

        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("username", oAuth2User.getAttribute("name"));
        body.put("mensaje", String.format("Hola %s, has iniciado sesión con éxito",  oAuth2User.getAttribute("name")));

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
    }
}