package com.jaroso;

import com.jaroso.entidades.Categoria;
import com.jaroso.entidades.Juego;
import com.jaroso.entidades.Jugador;
import com.jaroso.entidades.Plataforma;
import com.jaroso.repositorios.RepositorioJuego;
import com.jaroso.repositorios.RepositorioJugador;
import jakarta.transaction.Transactional;

public class App {

    @Transactional
    public static void main(String[] args) {

        //Creamos el repositorio
        RepositorioJugador repositorioJugador = new RepositorioJugador();
        RepositorioJuego repositorioJuego = new RepositorioJuego();

        Jugador j1 = new Jugador("Topeto14", "topeto@gmail.com", 32, "España");
        Juego juego1 = new Juego("Counter Strike", Plataforma.PC, 18, Categoria.DISPAROS);
        repositorioJugador.insertarJugador(j1);
        repositorioJuego.insertarJuego(juego1);

        //Añadimos el juego preferido al jugador Topeto
        j1.setJuegoPreferido(juego1);

        repositorioJugador.insertarJugador(j1);
        repositorioJuego.cerrarSession();
        repositorioJugador.cerrarSession();


    }
}
