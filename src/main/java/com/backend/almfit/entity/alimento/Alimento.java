package com.backend.almfit.entity.alimento;

import com.backend.almfit.entity.consumo.ConsumoDiario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alimento")
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double proteinas;

    @Column(nullable = false)
    private double carbohidratos;

    @Column(nullable = false)
    private double grasas;

    @Column(nullable = false)
    private double calorias100;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "alimento_categorias",
            joinColumns = @JoinColumn(name = "alimento_id"),
            inverseJoinColumns = @JoinColumn(name = "alimento_type_id")
    )
    @Column(nullable = true)
    private Set<AlimentoType> categorias = new HashSet<>();

    @OneToMany(mappedBy = "alimento")
    private Set<ConsumoDiario> consumosDiarios;

}
