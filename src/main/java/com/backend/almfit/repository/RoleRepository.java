package com.backend.almfit.repository;

import com.backend.almfit.entity.User.Role;
import com.backend.almfit.entity.User.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleType name);

    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Optional<Role> findByNameOptional(@Param("name") RoleType name);

}

