package com.jurados.services;

import com.jurados.entities.VotacaoPopular;
import com.jurados.repositories.VotacaoPopularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotacaoPopularService {

    @Autowired
    private VotacaoPopularRepository votacaoPopularRepository;

    public Optional<VotacaoPopular> buscarVoto(Long colaboradorId, Long eventoId) {
        return votacaoPopularRepository.findByColaboradorIdAndEventoId(colaboradorId, eventoId);
    }

    public VotacaoPopular registrarVoto(VotacaoPopular votacaoPopular) {
        return votacaoPopularRepository.save(votacaoPopular);
    }
}
