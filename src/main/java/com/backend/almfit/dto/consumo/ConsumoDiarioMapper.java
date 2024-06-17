package com.backend.almfit.dto.consumo;

import com.backend.almfit.entity.User.Usuario;
import com.backend.almfit.entity.alimento.Alimento;
import com.backend.almfit.entity.consumo.ConsumoDiario;
import com.backend.almfit.repository.AlimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConsumoDiarioMapper {

    public ConsumoDiarioDTO toDTO(ConsumoDiario consumoDiario) {
        return ConsumoDiarioDTO.builder()
                .id(consumoDiario.getId())
                .alimentoId(consumoDiario.getAlimento().getId())
                .alimentoNombre(consumoDiario.getAlimento().getNombre())
                .cantidad(consumoDiario.getCantidad())
                .proteinas(consumoDiario.getAlimento().getProteinas() * consumoDiario.getCantidad())
                .carbohidratos(consumoDiario.getAlimento().getCarbohidratos() * consumoDiario.getCantidad())
                .grasas(consumoDiario.getAlimento().getGrasas() * consumoDiario.getCantidad())
                .caloriasPorcion(consumoDiario.getAlimento().getCalorias100() * consumoDiario.getCantidad())
                .fecha(consumoDiario.getFecha())
                .build();
    }

    public ConsumoDiario toEntity(ConsumoDiarioDTO consumoDiarioDTO, Alimento alimento, Usuario usuario) {
        ConsumoDiario consumoDiario = new ConsumoDiario();
        consumoDiario.setAlimento(alimento);
        consumoDiario.setUsuario(usuario);
        consumoDiario.setCantidad(consumoDiarioDTO.getCantidad());
        consumoDiario.setFecha(consumoDiarioDTO.getFecha() != null ? consumoDiarioDTO.getFecha() : LocalDate.now());
        return consumoDiario;
    }
}
