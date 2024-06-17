package com.backend.almfit.service;

import com.backend.almfit.dto.user.RoleDTO;
import com.backend.almfit.dto.user.RoleMapper;
import com.backend.almfit.entity.User.Role;
import com.backend.almfit.entity.User.RoleType;
import com.backend.almfit.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = RoleMapper.toRole(roleDTO);
        Role savedRole = roleRepository.save(role);
        return RoleMapper.toRoleDTO(savedRole);
    }

    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(RoleMapper::toRoleDTO)
                .collect(Collectors.toList());
    }

    public RoleDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return RoleMapper.toRoleDTO(role);
    }

    public RoleDTO updateRole(Long id, RoleDTO roleDTO) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        role.setName(RoleType.valueOf(roleDTO.getName()));
        Role updatedRole = roleRepository.save(role);
        return RoleMapper.toRoleDTO(updatedRole);
    }

    public Optional<RoleDTO> findByName(String name) {
        try {
            RoleType roleType = RoleType.valueOf(name);
            return roleRepository.findByNameOptional(roleType).map(RoleMapper::toRoleDTO);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role name: " + name);
        }
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

}

