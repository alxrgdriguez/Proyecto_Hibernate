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

        Jugador jugador1 = new Jugador("Topeto14", "topeto@gmail.com");
        repositorioJugador.insertarJugador(jugador1);



        /*repositorioJugador.eliminarJugador(jugador1);
        repositorioJugador.eliminarJugador(repositorioJugador.findById(2L));*/
        repositorioJugador.cerrarSession();



    }
}
