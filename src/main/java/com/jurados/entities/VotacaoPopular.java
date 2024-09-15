package com.jurados.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "votacoes_populares")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotacaoPopular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "ideia_id")
    private Ideia ideia;
}
