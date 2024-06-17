package com.backend.almfit.dto.alimento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlimentoTypeDTO {
    private Long id;
    private String nombre;
}

