package com.backend.almfit.service;

import com.backend.almfit.auth.JwtTokenUtils;
import com.backend.almfit.dto.consumo.ConsumoDiarioDTO;
import com.backend.almfit.dto.consumo.ConsumoDiarioMapper;
import com.backend.almfit.entity.User.Usuario;
import com.backend.almfit.entity.alimento.Alimento;
import com.backend.almfit.entity.consumo.ConsumoDiario;
import com.backend.almfit.repository.AlimentoRepository;
import com.backend.almfit.repository.ConsumoDiarioRepository;
import com.backend.almfit.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsumoDiarioService {

    @Autowired
    private ConsumoDiarioRepository consumoDiarioRepository;

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private AlimentoRepository alimentoRepository;

    @Autowired
    private ConsumoDiarioMapper consumoDiarioMapper;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    public List<ConsumoDiarioDTO> getConsumoDiario(String token) {
        String username = jwtTokenUtils.getUsernameFromToken(token);
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        LocalDate fechaActual = LocalDate.now();
        List<ConsumoDiario> consumos = consumoDiarioRepository.findByUsuarioAndFecha(usuario, fechaActual);

        return consumos.stream().map(consumoDiarioMapper::toDTO).collect(Collectors.toList());
    }

    public List<ConsumoDiarioDTO> getConsumoDiarioPorFecha(String token, LocalDate fecha) {
        String username = jwtTokenUtils.getUsernameFromToken(token);
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<ConsumoDiario> consumos = consumoDiarioRepository.findByUsuarioAndFecha(usuario, fecha);

        return consumos.stream().map(consumoDiarioMapper::toDTO).collect(Collectors.toList());
    }

    public ConsumoDiarioDTO createConsumoDiario(String token, ConsumoDiarioDTO consumoDiarioDTO) {
        String username = jwtTokenUtils.getUsernameFromToken(token);
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Alimento alimento = alimentoRepository.findById(consumoDiarioDTO.getAlimentoId()).orElseThrow(() -> new RuntimeException("Alimento no encontrado"));

        ConsumoDiario consumoDiario = ConsumoDiario.builder()
                .usuario(usuario)
                .alimento(alimento)
                .cantidad(consumoDiarioDTO.getCantidad())
                .fecha(Optional.ofNullable(consumoDiarioDTO.getFecha()).orElse(LocalDate.now()))
                .build();

        ConsumoDiario savedConsumoDiario = consumoDiarioRepository.save(consumoDiario);

        return consumoDiarioMapper.toDTO(savedConsumoDiario);
    }

    public ConsumoDiarioDTO updateConsumoDiario(Long id, String token, double nuevaCantidad) {
        String username = jwtTokenUtils.getUsernameFromToken(token);
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        ConsumoDiario consumoDiario = consumoDiarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Consumo diario no encontrado"));

        if (!consumoDiario.getUsuario().equals(usuario)) {
            throw new RuntimeException("No autorizado para actualizar este consumo diario");
        }

        consumoDiario.setCantidad(nuevaCantidad);

        ConsumoDiario updatedConsumoDiario = consumoDiarioRepository.save(consumoDiario);

        return consumoDiarioMapper.toDTO(updatedConsumoDiario);
    }

    public void deleteConsumoDiario(Long id) {
        consumoDiarioRepository.deleteById(id);
    }
}
