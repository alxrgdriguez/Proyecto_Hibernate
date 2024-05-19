package com.jaroso.controladores;

import com.jaroso.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControladorIndex implements Initializable {

    @FXML
    public Button botonRegistroJugador;
    @FXML
    public Button botonRegistroJuego;
    @FXML
    public Button botonRegistroPartida;
    @FXML
    public Button botonConsultarEstadisticas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        botonRegistroJugador = new Button("Registro de Jugadores");
        botonRegistroJuego = new Button("Registro de Juegos");
        botonRegistroPartida = new Button("Registro de Partidas");
        botonConsultarEstadisticas = new Button("Consultar Estadisticas");

    }


    public void AbrirRegistroJuegos(MouseEvent mouseEvent) throws IOException {

        //Para abrir el registro de jugadores
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("registroJuegos.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Registro de Juegos");
        stage.setScene(scene);
        stage.show();
    }
}
