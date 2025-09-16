package com.avaliacao.computador.domain.model.computador.model;

import com.avaliacao.computador.domain.model.periferico.model.Periferico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@Entity
@Table(name = "computador")
@AllArgsConstructor
@NoArgsConstructor
public class Computador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cor")
    private String cor;

    @Column(name = "dataFabricacao")
    private Integer dataFabricacao;

    @OneToMany(mappedBy = "computador")
    @Builder.Default
    private List<Periferico> perifericos = new ArrayList<>();
}
