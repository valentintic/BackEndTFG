package com.backend.almfit.repository;

import com.backend.almfit.entity.User.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Optional<Usuario> findByEmailUsuario(String email);

    @Query("SELECT u FROM Usuario u WHERE u.username = :username")
    Optional<Usuario> getUserByUsername(String username);

}
