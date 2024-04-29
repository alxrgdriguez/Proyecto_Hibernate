package com.jaroso;

import com.jaroso.entidades.Juego;
import com.jaroso.entidades.Jugador;
import com.jaroso.repositorios.RepositorioJugador;
import jakarta.transaction.Transactional;

public class App {

    @Transactional
    public static void main(String[] args) {

        //Creamos el repositorio
        RepositorioJugador repositorioJugador = new RepositorioJugador();

        Jugador j1 = new Jugador("Topeto14", "topeto@gmail.com", 32, "España");
        repositorioJugador.insertarJugador(j1);

        /*repositorioJugador.eliminarJugador(j1);
        repositorioJugador.eliminarJugador(repositorioJugador.findById(2L));*/
        repositorioJugador.cerrarSession();



    }
}
