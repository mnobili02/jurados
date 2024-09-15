package com.jurados.services;

import com.jurados.entities.Evento;
import com.jurados.repositories.EventoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventoServiceTest {

    @InjectMocks
    private EventoService eventoService;

    @Mock
    private EventoRepository eventoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarEvento() {
        Evento evento = new Evento(null, "Evento Teste", "Descrição", LocalDate.now(), LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2), LocalDate.now().plusDays(3), null);
        when(eventoRepository.save(evento)).thenReturn(evento);

        Evento resultado = eventoService.criarEvento(evento);

        assertNotNull(resultado);
        assertEquals("Evento Teste", resultado.getNome());
        verify(eventoRepository, times(1)).save(evento);
    }

    @Test
    public void testBuscarEventoPorId() {
        Evento evento = new Evento(1L, "Evento Teste", "Descrição", LocalDate.now(), LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2), LocalDate.now().plusDays(3), null);
        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));

        Optional<Evento> resultado = eventoService.buscarEventoPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Evento Teste", resultado.get().getNome());
    }

    @Test
    public void testListarTodosEventos() {
        // Implemente o teste conforme necessário para o seu caso
    }
}
