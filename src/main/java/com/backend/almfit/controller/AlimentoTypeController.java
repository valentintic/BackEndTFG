package com.backend.almfit.controller;

import com.backend.almfit.dto.alimento.AlimentoTypeDTO;
import com.backend.almfit.service.AlimentoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alimentos/types")
@CrossOrigin(origins = "http://localhost:3000")
public class AlimentoTypeController {

    @Autowired
    private AlimentoTypeService alimentoTypeService;

    @GetMapping
    public ResponseEntity<List<AlimentoTypeDTO>> getAllAlimentoTypes() {
        List<AlimentoTypeDTO> alimentoTypes = alimentoTypeService.findAll();
        return new ResponseEntity<>(alimentoTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlimentoTypeDTO> getAlimentoTypeById(@PathVariable Long id) {
        AlimentoTypeDTO alimentoTypeDTO = alimentoTypeService.findById(id);
        return new ResponseEntity<>(alimentoTypeDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlimentoTypeDTO> createAlimentoType(@RequestBody AlimentoTypeDTO alimentoTypeDTO) {
        AlimentoTypeDTO createdAlimentoType = alimentoTypeService.save(alimentoTypeDTO);
        return new ResponseEntity<>(createdAlimentoType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlimentoTypeDTO> updateAlimentoType(@PathVariable Long id, @RequestBody AlimentoTypeDTO alimentoTypeDTO) {
        alimentoTypeDTO.setId(id); // Asignar el ID para la actualización
        AlimentoTypeDTO updatedAlimentoType = alimentoTypeService.updateAlimentoType(alimentoTypeDTO);
        return new ResponseEntity<>(updatedAlimentoType, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlimentoType(@PathVariable Long id) {
        alimentoTypeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
