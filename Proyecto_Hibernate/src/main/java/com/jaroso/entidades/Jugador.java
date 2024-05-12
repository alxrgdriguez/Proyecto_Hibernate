package com.jaroso.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickJugador;

    private Integer edad;

    private String email;

    private String idioma;

    private String pais;

    @ManyToOne
    private Juego juegoPreferido;


    /**
     * Constructor TEST
     */
    public Jugador(String nickJugador, String email, Integer edad, String pais) {
        this.nickJugador = nickJugador;
        this.email = email;
        this.edad = edad;
        this.pais = pais;
    }
}
