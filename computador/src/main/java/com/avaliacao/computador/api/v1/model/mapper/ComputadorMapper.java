package com.avaliacao.computador.api.v1.model.mapper;

import com.avaliacao.computador.api.v1.model.request.ComputadorRequestDTO;
import com.avaliacao.computador.api.v1.model.response.ComputadorResponseDTO;
import com.avaliacao.computador.domain.model.computador.model.Computador;
import java.util.stream.Collectors;
import com.avaliacao.computador.api.v1.model.mapper.PerifericoMapper;

public class ComputadorMapper {

    public static ComputadorResponseDTO toDTO(Computador computador){
        return ComputadorResponseDTO.builder()
                .id(computador.getId())
                .nome(computador.getNome())
                .cor(computador.getCor())
                .dataFabricacao(computador.getDataFabricacao())
                .perifericos(computador.getPerifericos() == null ? null : computador.getPerifericos().stream().map(PerifericoMapper::toDTO).collect(Collectors.toList()))
                .build();
    }

    public static Computador toModel(ComputadorRequestDTO computadorRequestDTO) {
        return Computador.builder()
                .nome(computadorRequestDTO.getNome())
                .cor(computadorRequestDTO.getCor())
                .dataFabricacao(computadorRequestDTO.getDataFabricacao())
                .build();
    }
}
