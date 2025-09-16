package com.avaliacao.computador.api.v1.model.mapper;

import com.avaliacao.computador.api.v1.model.request.PerifericoRequestDTO;
import com.avaliacao.computador.api.v1.model.response.PerifericoResponseDTO;
import com.avaliacao.computador.domain.model.periferico.model.Periferico;

public class PerifericoMapper {

    public static PerifericoResponseDTO toDTO(Periferico periferico) {
        return PerifericoResponseDTO.builder()
                .id(periferico.getId())
                .nome(periferico.getNome())
                .build();
    }

    public static Periferico toModel(PerifericoRequestDTO perifericoRequestDTO) {
        return Periferico.builder()
                .nome(perifericoRequestDTO.getNome())
                .build();
    }
}
