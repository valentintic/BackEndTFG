package com.backend.almfit.repository;


import com.backend.almfit.entity.User.Usuario;
import com.backend.almfit.entity.consumo.ConsumoDiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsumoDiarioRepository extends JpaRepository<ConsumoDiario, Long> {
    List<ConsumoDiario> findByUsuarioAndFecha(Usuario usuario, LocalDate fecha);
    List<ConsumoDiario> findByUsuario(Usuario usuario);
}
