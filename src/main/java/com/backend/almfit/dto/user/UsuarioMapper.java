package com.backend.almfit.dto.user;

import com.backend.almfit.entity.User.Usuario;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    public static UsuarioDTO toUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .email(usuario.getEmail())
                .firstName(usuario.getFirstName())
                .lastName(usuario.getLastName())
                .birthDate(usuario.getBirthDate())
                .createdAt(usuario.getCreatedAt())
                .roles(usuario.getRoles().stream()
                        .map(RoleMapper::toRoleDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static Usuario toUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .id(usuarioDTO.getId())
                .username(usuarioDTO.getUsername())
                .password(usuarioDTO.getPassword())
                .email(usuarioDTO.getEmail())
                .firstName(usuarioDTO.getFirstName())
                .lastName(usuarioDTO.getLastName())
                .birthDate(usuarioDTO.getBirthDate())
                .createdAt(usuarioDTO.getCreatedAt())
                .roles(usuarioDTO.getRoles().stream()
                        .map(RoleMapper::toRole)
                        .collect(Collectors.toSet()))
                .build();
    }
}


