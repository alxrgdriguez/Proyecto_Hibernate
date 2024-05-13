package com.jaroso.entidades;

import com.jaroso.enums.Categoria;
import com.jaroso.enums.Plataforma;
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

    @Enumerated (value = EnumType.STRING)
    private Categoria categoria;

    private Integer pegi;

    public Juego(String nombre, Plataforma plataforma, Categoria categoria, Integer pegi) {
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.categoria = categoria;
        this.pegi = pegi;
    }
}
