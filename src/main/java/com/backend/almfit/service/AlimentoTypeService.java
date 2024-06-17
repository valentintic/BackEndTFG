package com.backend.almfit.service;

import com.backend.almfit.dto.alimento.AlimentoTypeDTO;
import com.backend.almfit.dto.alimento.AlimentoTypeMapper;
import com.backend.almfit.entity.alimento.AlimentoType;
import com.backend.almfit.repository.AlimentoTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlimentoTypeService {

    private final AlimentoTypeRepository alimentoTypeRepository;

    private final AlimentoTypeMapper alimentoTypeMapper;

    public List<AlimentoTypeDTO> findAll() {
        return alimentoTypeRepository.findAll().stream()
                .map(AlimentoTypeMapper::toDTO)
                .collect(Collectors.toList());
    }
    public AlimentoType findByNombre(String nombre) {
        return alimentoTypeRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("AlimentoType not found"));
    }

    public AlimentoTypeDTO findByNombreDTO(String nombre) {
        return AlimentoTypeMapper.toDTO(alimentoTypeRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("AlimentoType not found")));
    }

    public AlimentoTypeDTO findById(Long id) {
        return AlimentoTypeMapper.toDTO(alimentoTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AlimentoType not found")));
    }

    public AlimentoTypeDTO save(AlimentoTypeDTO alimentoTypeDTO) {
        return AlimentoTypeMapper.toDTO(alimentoTypeRepository.save(AlimentoTypeMapper.toEntity(alimentoTypeDTO)));
    }

    public List<AlimentoTypeDTO> saveAll (List<AlimentoTypeDTO> alimentoTypeDTOList) {
        return alimentoTypeRepository.saveAll(alimentoTypeDTOList.stream().map(AlimentoTypeMapper::toEntity).collect(Collectors.toList()))
                .stream()
                .map(AlimentoTypeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AlimentoTypeDTO updateAlimentoType(AlimentoTypeDTO alimentoTypeDTO) {
        return AlimentoTypeMapper.toDTO(alimentoTypeRepository.save(AlimentoTypeMapper.toEntity(alimentoTypeDTO)));
    }

    public void deleteById(Long id) {
        alimentoTypeRepository.deleteById(id);
    }

}
