package com.jurados.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "colaboradores")
@Data // Lombok já fornece Getter, Setter, toString, EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Perfil perfil;

    public enum Perfil {
        COLABORADOR, ADMIN, AVALIADOR
    }
}

