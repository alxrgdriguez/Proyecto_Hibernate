package com.jaroso.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJugador;

    private String nickJugador;

    private Integer edad;

    private String email;

    private String idioma;

    @ManyToOne
    private Juego juegoPreferido;

    private String pais;


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
