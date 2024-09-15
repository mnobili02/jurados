package com.jurados.controllers;

import com.jurados.entities.Colaborador;
import com.jurados.services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {
    @Autowired
    private ColaboradorService colaboradorService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Colaborador> cadastrarColaborador(@RequestBody Colaborador colaborador) {
        Colaborador novoColaborador = colaboradorService.cadastrarColaborador(colaborador);
        return ResponseEntity.ok(novoColaborador);
    }

    @PatchMapping("/alterar-perfil/{id}")
    public ResponseEntity<Colaborador> alterarPerfil(@PathVariable Long id, @RequestParam Colaborador.Perfil perfil) {
        return colaboradorService.alterarPerfil(id, perfil)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Colaborador>> listarTodos() {
        return ResponseEntity.ok(colaboradorService.listarTodos());
    }
}

