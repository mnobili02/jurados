package com.jurados.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ideias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ideia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String impacto;

    @Column(nullable = false)
    private Double custoEstimado;

    @Column(nullable = false, length = 1000)
    private String descricao;

    @Column(nullable = false)
    private int votosPopulares = 0;

    @Column(nullable = false)
    private double mediaAvaliacoes = 0.0;


    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;
}
