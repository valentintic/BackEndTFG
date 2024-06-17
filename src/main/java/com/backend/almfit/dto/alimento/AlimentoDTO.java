package com.backend.almfit.dto.alimento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlimentoDTO {
    private Long id;
    private String nombre;
    private double proteinas;
    private double carbohidratos;
    private double grasas;
    private double caloriasPorcion;
    private List<AlimentoTypeDTO> tipos;
}
