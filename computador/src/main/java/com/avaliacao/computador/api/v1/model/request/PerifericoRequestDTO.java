package com.avaliacao.computador.api.v1.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerifericoRequestDTO {
    private String nome;
    private Long computadorId;
}
