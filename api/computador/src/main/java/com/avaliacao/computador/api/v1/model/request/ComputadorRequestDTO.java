package com.avaliacao.computador.api.v1.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ComputadorRequestDTO {
    private String nome;
    private String cor;
    private int dataFabricacao;
}
