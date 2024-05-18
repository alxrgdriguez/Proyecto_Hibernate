package com.jaroso.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora;

    private Integer duracion;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_juego")
    private Juego juego;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_jugador")
    private Jugador ganador;

    @ManyToMany
    @JoinTable (
            name = "participantes",
            joinColumns = @JoinColumn (
                    name = "id_partida",
                    referencedColumnName = "id" /*Hace referencia al id de Partida*/
            ),
            inverseJoinColumns = @JoinColumn (
                    name = "id_jugador",
                    referencedColumnName = "id" /*Hace referencia al id del Jugador*/
            )
    )
    private List<Jugador> participantes;

}
