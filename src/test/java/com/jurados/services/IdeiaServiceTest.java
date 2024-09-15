package com.jurados.services;

import com.jurados.entities.Ideia;
import com.jurados.repositories.IdeiaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IdeiaServiceTest {

    @InjectMocks
    private IdeiaService ideiaService;

    @Mock
    private IdeiaRepository ideiaRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPostarIdeia() {
        Ideia ideia = new Ideia(null, "Ideia Teste", "Impacto alto", 1000.0, "Descrição Teste", 0, 0.0, null, null);
        when(ideiaRepository.save(ideia)).thenReturn(ideia);

        Ideia resultado = ideiaService.postarIdeia(ideia);

        assertNotNull(resultado);
        assertEquals("Ideia Teste", resultado.getNome());
        verify(ideiaRepository, times(1)).save(ideia);
    }

    @Test
    public void testVotarNaIdeia() {
        Ideia ideia = new Ideia(1L, "Ideia Teste", "Impacto alto", 1000.0, "Descrição Teste", 0, 0.0, null, null);
        when(ideiaRepository.findById(1L)).thenReturn(Optional.of(ideia));
        when(ideiaRepository.save(ideia)).thenReturn(ideia);

        Ideia resultado = ideiaService.votarNaIdeia(1L);

        assertNotNull(resultado);
        assertEquals(1, resultado.getVotosPopulares());
        verify(ideiaRepository, times(1)).save(ideia);
    }

    @Test
    public void testListarTop10Ideias() {
        // Implemente o teste conforme necessário para o seu caso
    }
}
