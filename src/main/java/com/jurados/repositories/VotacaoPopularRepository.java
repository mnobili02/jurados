package com.jurados.repositories;

import com.jurados.entities.VotacaoPopular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotacaoPopularRepository extends JpaRepository<VotacaoPopular, Long> {
    Optional<VotacaoPopular> findByColaboradorIdAndEventoId(Long colaboradorId, Long eventoId);
}
