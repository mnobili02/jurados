package com.jurados.services;

import com.jurados.entities.Ideia;
import com.jurados.repositories.IdeiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdeiaService {

    @Autowired
    private IdeiaRepository ideiaRepository;

    public Ideia postarIdeia(Ideia ideia) {
        return ideiaRepository.save(ideia);
    }

    public List<Ideia> listarTodasIdeias() {
        return ideiaRepository.findAll();
    }

    public Ideia votarNaIdeia(Long ideiaId) {
        Ideia ideia = ideiaRepository.findById(ideiaId).orElseThrow(() -> new RuntimeException("Ideia não encontrada!"));
        ideia.setVotosPopulares(ideia.getVotosPopulares() + 1);
        return ideiaRepository.save(ideia);
    }

    public List<Ideia> listarTop10Ideias() {
        return ideiaRepository.findAll().stream()
                .sorted((i1, i2) -> {
                    int comp = Double.compare(i2.getMediaAvaliacoes(), i1.getMediaAvaliacoes());
                    if (comp == 0) {
                        comp = Integer.compare(i2.getVotosPopulares(), i1.getVotosPopulares());
                    }
                    return comp;
                })
                .limit(10)
                .toList();
    }
}
