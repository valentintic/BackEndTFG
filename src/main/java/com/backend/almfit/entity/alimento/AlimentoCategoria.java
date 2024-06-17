package com.backend.almfit.entity.alimento;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "alimento_categorias")
public class AlimentoCategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "alimento_id", nullable = false)
    private Alimento alimento;

    @ManyToOne
    @JoinColumn(name = "alimento_type_id", nullable = false)
    private AlimentoType categoria;
}
