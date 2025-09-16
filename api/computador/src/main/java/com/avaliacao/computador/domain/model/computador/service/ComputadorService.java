package com.avaliacao.computador.domain.model.computador.service;

import com.avaliacao.computador.domain.model.computador.model.Computador;
import com.avaliacao.computador.domain.model.computador.repository.ComputadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComputadorService {

    private final ComputadorRepository computadorRepository;

    @Transactional(readOnly = true)
    public List<Computador> getAllComputador() {
        var computadores = computadorRepository.findAll();
        if (computadores.isEmpty()) {
            return new ArrayList<>();
        }
        return computadores;
    }

    @Transactional(readOnly = true)
    public Computador getComputadorById(Long computadorId) {
        return computadorRepository.findById(computadorId)
                .orElseThrow(() -> new RuntimeException("Computador não encontrado"));
    }

    @Transactional
    public Computador createComputador(Computador computador) {
        return computadorRepository.save(computador);
    }

    @Transactional
    public void deleteComputador(Long computadorId) {
        var computadorExistente = this.getComputadorById(computadorId);
        if (computadorExistente.getPerifericos() != null) {
            computadorExistente.getPerifericos().forEach(periferico -> periferico.setComputador(null));
            computadorExistente.getPerifericos().clear();
        }
        computadorRepository.delete(computadorExistente);
    }

    @Transactional
    public Computador updateComputador(Long computadorId, Computador computador) {
        var computadorExistente = this.getComputadorById(computadorId);
        computadorExistente.setCor(computador.getCor());
        computadorExistente.setNome(computador.getNome());
        computadorExistente.setDataFabricacao(computador.getDataFabricacao());
        if (computador.getPerifericos() != null && !computador.getPerifericos().isEmpty()) {
            computadorExistente.setPerifericos(computador.getPerifericos());
        }
        return computadorRepository.save(computadorExistente);
    }
}
