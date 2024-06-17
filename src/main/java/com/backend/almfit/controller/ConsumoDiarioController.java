package com.backend.almfit.controller;

import com.backend.almfit.auth.JwtTokenUtils;
import com.backend.almfit.dto.consumo.ConsumoDiarioDTO;
import com.backend.almfit.entity.User.Usuario;
import com.backend.almfit.service.ConsumoDiarioService;
import com.backend.almfit.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/consumos")
@CrossOrigin(origins = "http://localhost:3000")
public class ConsumoDiarioController {

    @Autowired
    private ConsumoDiarioService consumoDiarioService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @GetMapping
    public ResponseEntity<List<ConsumoDiarioDTO>> getConsumoDiario(@RequestHeader("Authorization") String token, @RequestParam(value = "fecha", required = false) LocalDate fecha) {
        String jwt = token.substring(7); // Remove "Bearer " prefix
        if (fecha == null) {
            fecha = LocalDate.now();
        }
        List<ConsumoDiarioDTO> consumos = consumoDiarioService.getConsumoDiarioPorFecha(jwt, fecha);
        return ResponseEntity.ok(consumos);
    }

    @PostMapping
    public ResponseEntity<ConsumoDiarioDTO> createConsumoDiario(@RequestHeader("Authorization") String token, @RequestBody ConsumoDiarioDTO consumoDiarioDTO) {
        String jwt = token.substring(7); // Remove "Bearer " prefix
        ConsumoDiarioDTO createdConsumoDiario = consumoDiarioService.createConsumoDiario(jwt, consumoDiarioDTO);
        return ResponseEntity.ok(createdConsumoDiario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsumoDiario(@PathVariable Long id) {
        consumoDiarioService.deleteConsumoDiario(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumoDiarioDTO> updateConsumoDiario(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody double nuevaCantidad) {
        String jwt = token.substring(7); // Remove "Bearer " prefix
        ConsumoDiarioDTO updatedConsumoDiario = consumoDiarioService.updateConsumoDiario(id, jwt, nuevaCantidad);
        return ResponseEntity.ok(updatedConsumoDiario);
    }
}







