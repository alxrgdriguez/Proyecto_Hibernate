package com.jaroso.controladores;

import com.jaroso.entidades.Jugador;
import com.jaroso.repositorios.RepositorioJugador;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        RepositorioJugador jugador = new RepositorioJugador();
        Jugador j1 = jugador.findById(1L);
        welcomeText.setText(j1.toString());
    }
}