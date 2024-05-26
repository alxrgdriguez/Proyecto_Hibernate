package com.jaroso.controladores;

import com.jaroso.entidades.Juego;
import com.jaroso.entidades.Jugador;
import com.jaroso.repositorios.RepositorioJugador;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorRegistroJugadores implements Initializable {

    //MOSTRAR
    @FXML
    private TableView<Juego> tablajugadores;

    @FXML
    public TableColumn<Jugador, Long> idJugador;

    public TableColumn<Jugador, String> nickJugador;

    public TableColumn<Jugador, Integer> edadJugador;

    public TableColumn<Jugador, String> emailJugador;

    public TableColumn<Jugador, String> idiomaJugador;

    public TableColumn<Jugador, String> paisJugador;

    public TableColumn<Jugador, Juego> preferidoJugador;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
