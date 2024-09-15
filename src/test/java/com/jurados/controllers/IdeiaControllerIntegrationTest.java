package com.jurados.controllers;

import com.jurados.entities.Ideia;
import com.jurados.repositories.IdeiaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IdeiaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IdeiaRepository ideiaRepository;

    @BeforeEach
    public void setup() {
        ideiaRepository.deleteAll();
    }

    @Test
    public void testPostarIdeia() throws Exception {
        String ideiaJson = "{\"nome\": \"Ideia Teste\", \"impacto\": \"Alto\", \"custoEstimado\": 1000.0, \"descricao\": \"Descrição Teste\"}";

        mockMvc.perform(post("/ideias/postar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ideiaJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Ideia Teste"));
    }

    @Test
    public void testVotarNaIdeia() throws Exception {
        Ideia ideia = new Ideia(null, "Ideia Teste", "Impacto alto", 1000.0, "Descrição Teste", 0, 0.0, null, null);
        ideia = ideiaRepository.save(ideia);

        mockMvc.perform(patch("/ideias/votar/" + ideia.getId())
                        .param("colaboradorId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Voto registrado com sucesso!"));
    }
}
