package com.backend.almfit.controller;

import com.backend.almfit.dto.alimento.AlimentoDTO;
import com.backend.almfit.dto.alimento.AlimentoTypeDTO;
import com.backend.almfit.entity.alimento.Alimento;
import com.backend.almfit.service.AlimentoService;
import com.backend.almfit.service.AlimentoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alimentos")
@CrossOrigin(origins = "http://localhost:3000")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;
    @Autowired
    private AlimentoTypeService alimentoTypeService;

    @GetMapping
    public ResponseEntity<List<AlimentoDTO>> getAllAlimentos() {
        List<AlimentoDTO> alimentos = alimentoService.findAll();
        return new ResponseEntity<>(alimentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlimentoDTO> getAlimentoById(@PathVariable Long id) {
        AlimentoDTO alimento = alimentoService.findById(id);
        return new ResponseEntity<>(alimento, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlimentoDTO> createAlimento(@RequestBody AlimentoDTO alimentoDTO) {
        AlimentoDTO nuevoAlimento = alimentoService.save(alimentoDTO);
        return new ResponseEntity<>(nuevoAlimento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlimentoDTO> updateAlimento(@PathVariable Long id, @RequestBody AlimentoDTO alimentoDTO) {
        AlimentoDTO alimentoActualizado = alimentoService.updateAlimento(alimentoDTO);
        return new ResponseEntity<>(alimentoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlimento(@PathVariable Long id) {
        alimentoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Alimento>> findByCategoria(@PathVariable String categoria) {
        List<Alimento> alimentos = alimentoService.findByCategoria(alimentoTypeService.findByNombre(categoria));
        return new ResponseEntity<>(alimentos, HttpStatus.OK);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<AlimentoTypeDTO>> getAllCategorias() {
        List<AlimentoTypeDTO> categorias = alimentoTypeService.findAll();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

}
