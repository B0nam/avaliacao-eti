package com.avaliacao.computador.api.v1.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComputadorResponseDTO {
    private Long id;
    private String nome;
    private String cor;
    private Integer dataFabricacao;
    private List<PerifericoResponseDTO> perifericos;
}
