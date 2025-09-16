package com.avaliacao.computador.domain.model.computador.repository;

import com.avaliacao.computador.domain.model.computador.model.Computador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputadorRepository extends JpaRepository<Computador, Long> {
}
