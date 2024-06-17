package com.backend.almfit.config;

import com.backend.almfit.auth.JwtTokenUtils;
import com.backend.almfit.auth.filters.JwtAuthenticationFilter;
import com.backend.almfit.auth.filters.JwtValidationFilter;
import com.backend.almfit.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private JwtTokenUtils jwtUtil;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/roles").permitAll()
                        .requestMatchers("/roles/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuarios").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuarios/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/usuarios/{id}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/{id}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/alimentos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/alimentos/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/alimentos").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/alimentos").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/alimentos/{id}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/alimentos/{id}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/alimentos/categoria/{categoria}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/consumos").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/consumos").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/consumos/fecha/{fecha}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/consumos/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/consumos/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/consumos/{id}").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login -> oauth2Login
                        .defaultSuccessUrl("/oauth2/success", false)
                        .failureUrl("/login?error")
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                        .successHandler(customOAuth2UserService.getSuccessHandler())
                )
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        // Añadir los filtros JWT
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager(), jwtUtil), JwtValidationFilter.class);
        http.addFilterBefore(new JwtValidationFilter(authenticationManager(), jwtUtil), JwtAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {
        return new CustomOAuth2UserService();
    }
}