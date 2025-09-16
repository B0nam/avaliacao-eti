package com.avaliacao.computador.api.v1.controller;

import com.avaliacao.computador.domain.model.computador.service.ComputadorService;
import com.avaliacao.computador.domain.model.periferico.service.PerifericoService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/relacionar")
@RequiredArgsConstructor
public class RelacionarController {

    private final ComputadorService computadorService;
    private final PerifericoService perifericoService;

    @PostMapping("/{computadorId}/{perifericoId}")
    public ResponseEntity<Void> relacionarComputadorPeriferico(
            @Parameter(description = "ID do computador") @PathVariable Long computadorId,
            @Parameter(description = "ID do periférico") @PathVariable Long perifericoId) {
        var computador = computadorService.getComputadorById(computadorId);
        var periferico = perifericoService.getPerifericoById(perifericoId);
        periferico.setComputador(computador);
        if (!computador.getPerifericos().contains(periferico)) {
            computador.getPerifericos().add(periferico);
        }
        perifericoService.updatePeriferico(perifericoId, periferico);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{computadorId}/{perifericoId}")
    public ResponseEntity<Void> desrelacionarComputadorPeriferico(
            @Parameter(description = "ID do computador") @PathVariable Long computadorId,
            @Parameter(description = "ID do periférico") @PathVariable Long perifericoId) {
        var periferico = perifericoService.getPerifericoById(perifericoId);
        if (periferico.getComputador() != null && periferico.getComputador().getId().equals(computadorId)) {
            periferico.setComputador(null);
            perifericoService.updatePeriferico(perifericoId, periferico);
        }
        return ResponseEntity.status(200).build();
    }
}
