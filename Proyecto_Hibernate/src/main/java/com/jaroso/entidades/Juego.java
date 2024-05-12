package com.jaroso.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated (value = EnumType.STRING)
    private Plataforma plataforma;

    private Integer pegi;

    @Enumerated (value = EnumType.STRING)
    private Categoria categoria;

    public Juego(String nombre, Plataforma plataforma, Integer pegi, Categoria categoria) {
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.pegi = pegi;
        this.categoria = categoria;
    }
}
