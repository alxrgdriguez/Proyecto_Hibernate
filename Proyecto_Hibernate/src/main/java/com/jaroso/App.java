package com.jaroso;

import com.jaroso.entidades.Juego;
import com.jaroso.entidades.Jugador;
import com.jaroso.entidades.Partida;
import com.jaroso.enums.Categoria;
import com.jaroso.enums.Plataforma;
import com.jaroso.repositorios.RepositorioJuego;
import com.jaroso.repositorios.RepositorioJugador;
import com.jaroso.repositorios.RepositorioPartida;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("index-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Proyecto Hibernate");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void init() throws Exception {
        System.out.println("init() method: " + Thread.currentThread().getName());
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop() method: " + Thread.currentThread().getName());
    }

    public static int numaleatorio(int min, int max){

        return (int) (Math.random() * (max - min + 1) + min);

    }

    public static void main(String[] args) {

        RepositorioJuego repositorioJuego = new RepositorioJuego();
        RepositorioJugador repositorioJugador = new RepositorioJugador();
        RepositorioPartida repositorioPartida = new RepositorioPartida();

        for (int i = 1; i <= 50; i++) {

            int aleatoriaPlataforma = numaleatorio(0, 4);
            Plataforma plataforma = Plataforma.values()[aleatoriaPlataforma];

            int aleatorioCategoria = numaleatorio(0, 12);
            Categoria categoria = Categoria.values()[aleatorioCategoria];

            int aleatorioPegi = numaleatorio(0, 18);

            Juego juego = new Juego("Juego " + i, plataforma, aleatorioPegi, categoria);
            repositorioJuego.insertarJuego(juego);
        }

        for (int i = 1; i <= 50; i++) {
            int aleatorioEdad = numaleatorio(4, 120);
            String aleatorioNick = "Jugador " + i;
            String aleatorioEmail = "email" + i + "@gmail.com";
            String aleatorioIdioma = "Idioma " + i;
            String aleatorioPais = "Pais " + i;

            Juego juegoPreferido = repositorioJuego.findById(Long.parseLong(String.valueOf(numaleatorio(0,50))));

            Jugador jugador = new Jugador(aleatorioNick, aleatorioEmail, aleatorioEdad, aleatorioIdioma, aleatorioPais, juegoPreferido);
            repositorioJugador.insertarJugador(jugador);
        }

        for (int i = 1; i <= 50; i++) {
            int aleatorioDuracion = numaleatorio(1, 60);
            LocalDateTime fechaHora = LocalDateTime.now().minusDays(aleatorioDuracion);

            Juego juegoPartida = repositorioJuego.findById(Long.parseLong(String.valueOf(numaleatorio(0,50))));
            Jugador jugadorGanador = repositorioJugador.findById(Long.parseLong(String.valueOf(numaleatorio(0,50))));
            List<Jugador> participantes = repositorioJugador.findAll();

            Partida partida = new Partida(fechaHora, aleatorioDuracion, juegoPartida, jugadorGanador, participantes);
            repositorioPartida.insertarPartida(partida);
        }
        repositorioPartida.cerrarSession();
        repositorioJugador.cerrarSession();
        repositorioJuego.cerrarSession();

        //JAVAFX
        launch();


    }


}
