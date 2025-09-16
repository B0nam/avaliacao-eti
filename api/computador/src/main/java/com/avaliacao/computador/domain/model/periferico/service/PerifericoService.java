package com.avaliacao.computador.domain.model.periferico.service;

import com.avaliacao.computador.domain.model.periferico.model.Periferico;
import com.avaliacao.computador.domain.model.periferico.repository.PerifericoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerifericoService {

    private final PerifericoRepository perifericoRepository;

    @Transactional(readOnly = true)
    public List<Periferico> getAllPeriferico() {
        var perifericos = perifericoRepository.findAll();
        if (perifericos.isEmpty()) {
            return new ArrayList<>();
        }
        return perifericos;
    }

    @Transactional(readOnly = true)
    public Periferico getPerifericoById(Long perifericoId) {
        return perifericoRepository.findById(perifericoId)
                .orElseThrow(() -> new RuntimeException("Periférico não encontrado"));
    }

    @Transactional
    public Periferico createPeriferico(Periferico periferico) {
        return perifericoRepository.save(periferico);
    }

    @Transactional
    public Periferico deletePeriferico(Long perifericoId) {
        var perifericoExistente = this.getPerifericoById(perifericoId);
        perifericoRepository.delete(perifericoExistente);
        return perifericoExistente;
    }

    @Transactional
    public Periferico updatePeriferico(Long perifericoId, Periferico periferico) {
        var perifericoExistente = this.getPerifericoById(perifericoId);
        perifericoExistente.setNome(periferico.getNome());
        perifericoExistente.setComputador(periferico.getComputador());
        return perifericoRepository.save(perifericoExistente);
    }
}
