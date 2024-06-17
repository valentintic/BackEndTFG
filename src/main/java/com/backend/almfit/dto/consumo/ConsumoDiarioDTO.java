package com.backend.almfit.dto.consumo;

import com.backend.almfit.entity.User.Usuario;
import com.backend.almfit.entity.alimento.Alimento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class ConsumoDiarioDTO {
    private Long id;
    private Long alimentoId;
    private String alimentoNombre;
    private double cantidad;
    private double proteinas;
    private double carbohidratos;
    private double grasas;
    private double caloriasPorcion;
    private LocalDate fecha;
}