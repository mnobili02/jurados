package com.jurados.controllers;

import com.jurados.entities.Colaborador;
import com.jurados.entities.Evento;
import com.jurados.entities.Ideia;
import com.jurados.entities.VotacaoPopular;
import com.jurados.services.IdeiaService;
import com.jurados.services.VotacaoPopularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ideias")
public class IdeiaController {

    @Autowired
    private IdeiaService ideiaService;

    @PostMapping("/postar")
    public ResponseEntity<Ideia> postarIdeia(@RequestBody Ideia ideia) {
        Ideia novaIdeia = ideiaService.postarIdeia(ideia);
        return ResponseEntity.ok(novaIdeia);
    }

    @GetMapping
    public ResponseEntity<List<Ideia>> listarTodasIdeias() {
        return ResponseEntity.ok(ideiaService.listarTodasIdeias());
    }

    @PatchMapping("/votar/{id}")
    public ResponseEntity<Ideia> votarNaIdeia(@PathVariable Long id) {
        Ideia ideia = ideiaService.votarNaIdeia(id);
        return ResponseEntity.ok(ideia);
    }

    @Autowired
    private VotacaoPopularService votacaoPopularService;

    @PatchMapping("/votar/{ideiaId}")
    public ResponseEntity<String> votarNaIdeia(@PathVariable Long ideiaId, @RequestParam Long colaboradorId) {
        Ideia ideia = ideiaService.buscarPorId(ideiaId).orElseThrow(() -> new RuntimeException("Ideia não encontrada"));
        Evento evento = ideia.getEvento();

        if (votacaoPopularService.buscarVoto(colaboradorId, evento.getId()).isPresent()) {
            return ResponseEntity.badRequest().body("Colaborador já votou nesse evento!");
        }

        // Registra o voto
        VotacaoPopular votacaoPopular = new VotacaoPopular();
        votacaoPopular.setColaborador(new Colaborador(colaboradorId));
        votacaoPopular.setEvento(evento);
        votacaoPopular.setIdeia(ideia);
        votacaoPopularService.registrarVoto(votacaoPopular);

        ideiaService.votarNaIdeia(ideiaId);
        return ResponseEntity.ok("Voto registrado com sucesso!");
    }

    @GetMapping("/top10")
    public ResponseEntity<List<Ideia>> listarTop10Ideias() {
        return ResponseEntity.ok(ideiaService.listarTop10Ideias());
    }
}
