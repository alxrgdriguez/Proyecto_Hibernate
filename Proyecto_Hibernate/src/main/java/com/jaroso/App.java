package com.jaroso;

import com.jaroso.enums.Categoria;
import com.jaroso.entidades.Juego;
import com.jaroso.entidades.Jugador;
import com.jaroso.enums.Plataforma;
import com.jaroso.repositorios.RepositorioJuego;
import com.jaroso.repositorios.RepositorioJugador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Opción 2: utilizando plantillas FXML (Recomendable)
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("juego-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 690);
        stage.setTitle("Hello!");
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


    public static void main(String[] args) {

        //Creamos los repositorio
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

        launch();


    }


}
