package com.avaliacao.computador.api.v1.controller;

import com.avaliacao.computador.api.v1.model.mapper.ComputadorMapper;
import com.avaliacao.computador.api.v1.model.request.ComputadorRequestDTO;
import com.avaliacao.computador.api.v1.model.response.ComputadorResponseDTO;
import com.avaliacao.computador.domain.model.computador.service.ComputadorService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/computadores/")
@RequiredArgsConstructor
public class ComputadorController {

    private final ComputadorService computadorService;

    @GetMapping
    public ResponseEntity<List<ComputadorResponseDTO>> getAllComputador() {
        var computadores = computadorService.getAllComputador();
        return ResponseEntity.status(HttpStatus.OK).body(computadores.stream().map(ComputadorMapper::toDTO).toList());
    }

    @GetMapping("/{computadorId}")
    public ResponseEntity<ComputadorResponseDTO> getComputadorById(
            @Parameter(description = "ID do computador") @PathVariable Long computadorId) {
        var computador = computadorService.getComputadorById(computadorId);
        return ResponseEntity.status(HttpStatus.OK).body(ComputadorMapper.toDTO(computador));
    }

    @PostMapping
    public ResponseEntity<ComputadorResponseDTO> createComputador(@RequestBody ComputadorRequestDTO computadorRequestDTO) {
        var computador = computadorService.createComputador(ComputadorMapper.toModel(computadorRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(ComputadorMapper.toDTO(computador));
    }

    @DeleteMapping("/{computadorId}")
    public ResponseEntity deleteComputador(
            @Parameter(description = "ID do computador") @PathVariable Long computadorId) {
        computadorService.deleteComputador(computadorId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/{computadorId}")
    public ResponseEntity<ComputadorResponseDTO> updateComputador(
            @Parameter(description = "ID do computador") @PathVariable Long computadorId, @RequestBody ComputadorRequestDTO computadorRequestDTO) {
        var computador = computadorService.updateComputador(computadorId, ComputadorMapper.toModel(computadorRequestDTO));
        return ResponseEntity.status(HttpStatus.OK).body(ComputadorMapper.toDTO(computador));
    }
}
