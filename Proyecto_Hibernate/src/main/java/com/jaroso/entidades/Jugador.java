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

    /*private Juego juegoPreferido;*/

    private String pais;

    @Lob
    private byte[] avatar;

}
