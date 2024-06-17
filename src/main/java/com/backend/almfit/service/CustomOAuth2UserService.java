package com.backend.almfit.service;
import com.backend.almfit.auth.JwtTokenUtils;
import com.backend.almfit.entity.User.Role;
import com.backend.almfit.entity.User.RoleType;
import com.backend.almfit.entity.User.Usuario;
import com.backend.almfit.repository.RoleRepository;
import com.backend.almfit.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.*;


@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtTokenUtils jwtUtil;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        Optional<Usuario> optionalUser = usuarioRepository.findByEmail(email);
        Usuario user;

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName(RoleType.ROLE_USER));

            user = Usuario.builder()
                    .username(oAuth2User.getAttribute("name"))
                    .email(oAuth2User.getAttribute("email"))
                    .password(passwordEncoder.encode("password")) // Guardar la contraseña codificada
                    .firstName(oAuth2User.getAttribute("given_name"))
                    .lastName(oAuth2User.getAttribute("family_name"))
                    .createdAt(java.time.LocalDate.now())
                    .roles(roles)
                    .build();
            usuarioRepository.save(user);
        }

        Collection<GrantedAuthority> mappedAuthorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        String token;
        try {
            token = jwtUtil.createJwtToken(user.getUsername(), mappedAuthorities);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());
        attributes.put("token", token);
        attributes.put("username", user.getUsername());

        return new DefaultOAuth2User(
                mappedAuthorities,
                attributes,
                "email"
        );
    }

    public AuthenticationSuccessHandler getSuccessHandler() {
        return (request, response, authentication) -> {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            String username = oAuth2User.getAttribute("username");
            String token = oAuth2User.getAttribute("token");

            String redirectUrl = "http://localhost:3000/oauth2/redirect?username=" + username + "&token=" + token;
            response.sendRedirect(redirectUrl);
        };
    }
}
