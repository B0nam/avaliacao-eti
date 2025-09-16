package com.avaliacao.computador.api.v1.controller;

import com.avaliacao.computador.api.v1.model.mapper.PerifericoMapper;
import com.avaliacao.computador.api.v1.model.request.PerifericoRequestDTO;
import com.avaliacao.computador.api.v1.model.response.PerifericoResponseDTO;
import com.avaliacao.computador.domain.model.periferico.service.PerifericoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/perifericos/")
@RequiredArgsConstructor
public class PerifericoController {

    private final PerifericoService perifericoService;

    @GetMapping
    public ResponseEntity<List<PerifericoResponseDTO>> getAllPeriferico() {
        var perifericos = perifericoService.getAllPeriferico();
        return ResponseEntity.status(HttpStatus.OK).body(perifericos.stream().map(PerifericoMapper::toDTO).toList());
    }

    @GetMapping("/{perifericoId}")
    public ResponseEntity<PerifericoResponseDTO> getPerifericoById(
            @Parameter(description = "ID do periferico") @PathVariable Long perifericoId) {
        var periferico = perifericoService.getPerifericoById(perifericoId);
        return ResponseEntity.status(HttpStatus.OK).body(PerifericoMapper.toDTO(periferico));
    }

    @PostMapping
    public ResponseEntity<PerifericoResponseDTO> createPeriferico(@RequestBody PerifericoRequestDTO perifericoRequestDTO) {
        var periferico = perifericoService.createPeriferico(PerifericoMapper.toModel(perifericoRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(PerifericoMapper.toDTO(periferico));
    }

    @DeleteMapping("/{perifericoId}")
    public ResponseEntity<Void> deletePeriferico(
            @Parameter(description = "ID do periferico") @PathVariable Long perifericoId) {
        perifericoService.deletePeriferico(perifericoId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/{perifericoId}")
    public ResponseEntity<PerifericoResponseDTO> updatePeriferico(
            @Parameter(description = "ID do perferico") @PathVariable Long perifericoId, @RequestBody PerifericoRequestDTO perifericoRequestDTO) {
        var periferico = perifericoService.updatePeriferico(perifericoId, PerifericoMapper.toModel(perifericoRequestDTO));
        return ResponseEntity.status(HttpStatus.OK).body(PerifericoMapper.toDTO(periferico)
    );
    }
}

