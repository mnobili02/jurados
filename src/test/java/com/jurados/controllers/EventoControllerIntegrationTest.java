package com.jurados.controllers;

import com.jurados.entities.Evento;
import com.jurados.repositories.EventoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EventoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventoRepository eventoRepository;

    @BeforeEach
    public void setup() {
        eventoRepository.deleteAll();
    }

    @Test
    public void testCriarEvento() throws Exception {
        String eventoJson = "{\"nome\": \"Evento Teste\", \"descricao\": \"Descrição\", \"dataInicio\": \"2024-09-01\", \"dataFim\": \"2024-09-10\", \"dataAvaliacaoJurados\": \"2024-09-15\", \"dataAvaliacaoPopular\": \"2024-09-20\"}";

        mockMvc.perform(post("/eventos/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Evento Teste"));
    }

    @Test
    public void testBuscarEventoPorId() throws Exception {
        Evento evento = new Evento(null, "Evento Teste", "Descrição", LocalDate.now(), LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2), LocalDate.now().plusDays(3), null);
        evento = eventoRepository.save(evento);

        mockMvc.perform(get("/eventos/" + evento.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Evento Teste"));
    }
}
