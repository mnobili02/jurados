package com.jurados.services;

import com.jurados.entities.Colaborador;
import com.jurados.repositories.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {
    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Colaborador cadastrarColaborador(Colaborador colaborador) {
        colaborador.setSenha(passwordEncoder.encode(colaborador.getSenha()));
        colaborador.setPerfil(Colaborador.Perfil.COLABORADOR);
        return colaboradorRepository.save(colaborador);
    }

    public Optional<Colaborador> alterarPerfil(Long id, Colaborador.Perfil perfil) {
        Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
        colaborador.ifPresent(c -> {
            c.setPerfil(perfil);
            colaboradorRepository.save(c);
        });
        return colaborador;
    }

    public List<Colaborador> listarTodos() {
        return colaboradorRepository.findAll();
    }

    public Optional<Colaborador> buscarPorEmail(String email) {
        return colaboradorRepository.findByEmail(email);
    }
}
