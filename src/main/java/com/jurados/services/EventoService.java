package com.jurados.services;

import com.jurados.entities.Evento;
import com.jurados.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Evento criarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public Optional<Evento> buscarEventoPorId(Long id) {
        return eventoRepository.findById(id);
    }

    public List<Evento> listarTodosEventos() {
        return eventoRepository.findAll();
    }
}