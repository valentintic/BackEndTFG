package com.backend.almfit.service;

import com.backend.almfit.dto.alimento.AlimentoDTO;
import com.backend.almfit.dto.alimento.AlimentoMapper;
import com.backend.almfit.dto.alimento.AlimentoTypeMapper;
import com.backend.almfit.entity.alimento.Alimento;
import com.backend.almfit.entity.alimento.AlimentoType;
import com.backend.almfit.repository.AlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlimentoService {

    private final AlimentoRepository alimentoRepository;

    private final AlimentoMapper alimentoMapper;
    private final AlimentoTypeMapper alimentoTypeMapper;


    public List<AlimentoDTO> findAll() {
        return alimentoRepository.findAll().stream()
                .map(alimentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AlimentoDTO findById(Long id) {
        return alimentoMapper.toDTO(alimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alimento not found")));
    }

    public AlimentoDTO save(AlimentoDTO alimentoDTO) {
        return alimentoMapper.toDTO(alimentoRepository.save(alimentoMapper.toEntity(alimentoDTO)));
    }

    public List<AlimentoDTO> saveAll (List<AlimentoDTO> alimentoDTOList) {
        return alimentoRepository.saveAll(alimentoDTOList.stream().map(alimentoMapper::toEntity).collect(Collectors.toList()))
                .stream()
                .map(alimentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AlimentoDTO updateAlimento(AlimentoDTO alimentoDTO) {
        return alimentoMapper.toDTO(alimentoRepository.save(alimentoMapper.toEntity(alimentoDTO)));
    }

    public void deleteById(Long id) {
        alimentoRepository.deleteById(id);
    }

    public List<AlimentoDTO> getAlimentosInCategorias(String categorias) {
        List<Alimento> alimentos = alimentoRepository.findByCategoria(categorias);
        return alimentos.stream()
                .map(alimentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<Alimento> findByCategoria(AlimentoType nombreCategoria) {
        return alimentoRepository.findByCategoria(nombreCategoria);
    }

}
