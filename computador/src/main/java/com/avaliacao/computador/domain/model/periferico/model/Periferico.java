package com.avaliacao.computador.domain.model.periferico.model;

import com.avaliacao.computador.domain.model.computador.model.Computador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@Table(name = "periferico")
@AllArgsConstructor
@NoArgsConstructor
public class Periferico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computador_id")
    private Computador computador;
}
