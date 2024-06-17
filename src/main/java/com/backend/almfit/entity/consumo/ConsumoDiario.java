package com.backend.almfit.entity.consumo;

import com.backend.almfit.entity.User.Usuario;
import com.backend.almfit.entity.alimento.Alimento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consumo_diario")
public class ConsumoDiario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "alimento_id", nullable = false)
    private Alimento alimento;

    private double cantidad;
    private LocalDate fecha;
}
