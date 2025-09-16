package com.avaliacao.computador.domain.model.periferico.repository;

import com.avaliacao.computador.domain.model.periferico.model.Periferico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerifericoRepository extends JpaRepository<Periferico, Long> {
}
