package com.backend.almfit.dto.alimento;

import com.backend.almfit.entity.alimento.AlimentoType;
import org.springframework.stereotype.Component;

@Component

public class AlimentoTypeMapper {


    public static AlimentoTypeDTO toDTO(AlimentoType alimentoType) {
        AlimentoTypeDTO alimentoTypeDTO = new AlimentoTypeDTO();
        alimentoTypeDTO.setId(alimentoType.getId());
        alimentoTypeDTO.setNombre(alimentoType.getNombre());
        return alimentoTypeDTO;
    }

    public static AlimentoType toEntity(AlimentoTypeDTO alimentoTypeDTO) {
        AlimentoType alimentoType = new AlimentoType();
        alimentoType.setId(alimentoTypeDTO.getId());
        alimentoType.setNombre(alimentoTypeDTO.getNombre());
        return alimentoType;
    }
}
