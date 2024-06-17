package com.backend.almfit.repository;

import com.backend.almfit.entity.alimento.Alimento;
import com.backend.almfit.entity.alimento.AlimentoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

    @Query("SELECT a FROM Alimento a JOIN a.categorias c WHERE c.nombre = :nombreCategoria")
    List<Alimento> findByCategoria(String nombreCategoria);

    @Query("SELECT a FROM Alimento a JOIN a.categorias c WHERE c = :categoria")
    List<Alimento> findByCategoria(@Param("categoria") AlimentoType categoria);


}
