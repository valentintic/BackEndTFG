package com.backend.almfit.repository;

import com.backend.almfit.entity.alimento.AlimentoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlimentoTypeRepository extends JpaRepository<AlimentoType, Long> {

    Optional<AlimentoType> findByNombre(String nombre);
}
