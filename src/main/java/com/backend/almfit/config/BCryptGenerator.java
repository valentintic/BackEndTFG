package com.backend.almfit.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123"; // La contraseña en texto plano que deseas cifrar
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Encoded password for '123': " + encodedPassword);
    }
}
