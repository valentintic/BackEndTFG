package com.backend.almfit.dto.user;

import com.backend.almfit.entity.User.Role;
import com.backend.almfit.entity.User.RoleType;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public static RoleDTO toRoleDTO(Role role) {
        return new RoleDTO(role.getId(), role.getName().name());
    }

    public static Role toRole(RoleDTO roleDTO) {
        return new Role(roleDTO.getId(), RoleType.valueOf(roleDTO.getName()));
    }
}
