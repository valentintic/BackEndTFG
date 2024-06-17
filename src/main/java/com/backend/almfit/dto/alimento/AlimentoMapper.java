package com.backend.almfit.dto.alimento;

import com.backend.almfit.entity.alimento.Alimento;
import com.backend.almfit.entity.alimento.AlimentoType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AlimentoMapper {



    public AlimentoDTO toDTO(Alimento alimento) {
        AlimentoDTO alimentoDTO = new AlimentoDTO();
        alimentoDTO.setId(alimento.getId());
        alimentoDTO.setNombre(alimento.getNombre());
        alimentoDTO.setProteinas(alimento.getProteinas());
        alimentoDTO.setCarbohidratos(alimento.getCarbohidratos());
        alimentoDTO.setGrasas(alimento.getGrasas());
        alimentoDTO.setCaloriasPorcion(alimento.getCalorias100());
        if (alimento.getCategorias() != null) {
            List<AlimentoTypeDTO> tiposDTO = alimento.getCategorias().stream()
                    .map(AlimentoTypeMapper::toDTO)
                    .collect(Collectors.toList());
            alimentoDTO.setTipos(tiposDTO);
        }
        return alimentoDTO;
    }

    public Alimento toEntity(AlimentoDTO alimentoDTO) {
        Alimento alimento = new Alimento();
        alimento.setId(alimentoDTO.getId());
        alimento.setNombre(alimentoDTO.getNombre());
        alimento.setProteinas(alimentoDTO.getProteinas());
        alimento.setCarbohidratos(alimentoDTO.getCarbohidratos());
        alimento.setGrasas(alimentoDTO.getGrasas());
        alimento.setCalorias100(alimentoDTO.getCaloriasPorcion());
        if (alimentoDTO.getTipos() != null) {
            Set<AlimentoType> tipos = alimentoDTO.getTipos().stream()
                    .map(AlimentoTypeMapper::toEntity)
                    .collect(Collectors.toSet());
            alimento.setCategorias(tipos);
        }
        return alimento;
    }
}

