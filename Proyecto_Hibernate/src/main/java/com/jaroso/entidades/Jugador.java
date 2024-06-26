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
    private Long id;

    @Column (unique = true)
    private String nick;

    private Integer edad;

    @Column (unique = true)
    private String email;

    private String idioma;

    private String pais;

    @ManyToOne (fetch = FetchType.LAZY) /*Relacion unidireccional -> se carga solo el juego*/
    private Juego juegoPreferido;


    /**
     * Constructor TEST
     */
    public Jugador(String nick, String email, Integer edad, String idioma, String pais, Juego juegoPreferido) {
        this.nick = nick;
        this.email = email;
        this.edad = edad;
        this.idioma = idioma;
        this.pais = pais;
        this.juegoPreferido = juegoPreferido;
    }
}
