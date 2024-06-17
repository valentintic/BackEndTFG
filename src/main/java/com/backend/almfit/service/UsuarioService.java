package com.backend.almfit.service;

import com.backend.almfit.dto.user.RoleDTO;
import com.backend.almfit.dto.user.RoleMapper;
import com.backend.almfit.dto.user.UsuarioDTO;
import com.backend.almfit.dto.user.UsuarioMapper;
import com.backend.almfit.entity.User.Usuario;
import com.backend.almfit.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;


    public UsuarioDTO getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario not found"));
        return UsuarioMapper.toUsuarioDTO(usuario);
    }

    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioMapper::toUsuarioDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        Set<RoleDTO> roles;

        if (usuarioDTO.getRoles() == null || usuarioDTO.getRoles().isEmpty()) {
            // Asigna el rol por defecto si no se especifica ninguno
            roles = new HashSet<>(Collections.singletonList(new RoleDTO(1L, "ROLE_USER")));
        } else {
            // Obtén los roles del servicio y asegúrate de que existan
            roles = usuarioDTO.getRoles().stream()
                    .map(roleDTO -> roleService.findByName(roleDTO.getName())
                            .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleDTO.getName())))
                    .collect(Collectors.toSet());
        }

        usuarioDTO.setRoles(roles);

        Usuario usuario = UsuarioMapper.toUsuario(usuarioDTO);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setCreatedAt(LocalDate.now());

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return UsuarioMapper.toUsuarioDTO(savedUsuario);
    }

    public UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario not found"));

        // Actualiza los roles igual que en el método createUsuario
        Set<RoleDTO> roles;
        if (usuarioDTO.getRoles() == null || usuarioDTO.getRoles().isEmpty()) {
            roles = new HashSet<>(Collections.singletonList(new RoleDTO(1L, "ROLE_USER")));
        } else {
            roles = usuarioDTO.getRoles().stream()
                    .map(roleDTO -> roleService.findByName(roleDTO.getName())
                            .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleDTO.getName())))
                    .collect(Collectors.toSet());
        }

        // Si no se proporciona una nueva contraseña, mantiene la existente
        if (usuarioDTO.getPassword() == null || usuarioDTO.getPassword().isEmpty()) {
            usuarioDTO.setPassword(usuario.getPassword());
        } else {
            usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        }

        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setFirstName(usuarioDTO.getFirstName());
        usuario.setLastName(usuarioDTO.getLastName());
        usuario.setBirthDate(usuarioDTO.getBirthDate());
        usuario.setRoles(roles.stream().map(RoleMapper::toRole).collect(Collectors.toSet()));

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return UsuarioMapper.toUsuarioDTO(updatedUsuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO findByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Usuario not found with this email"));
        return UsuarioMapper.toUsuarioDTO(usuario);
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
