package com.jaroso.controladores;

import com.jaroso.entidades.Juego;
import com.jaroso.entidades.Jugador;
import com.jaroso.repositorios.RepositorioJugador;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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

    @FXML
    public TextField campoIdBuscar;

    public Button btn_buscar;

    public Button mostrarTodo;

    public Button mostrarId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idJugador.setCellValueFactory(new PropertyValueFactory<>("id"));
        nickJugador.setCellValueFactory(new PropertyValueFactory<>("nick"));
        edadJugador.setCellValueFactory(new PropertyValueFactory<>("edad"));
        emailJugador.setCellValueFactory(new PropertyValueFactory<>("email"));
        idiomaJugador.setCellValueFactory(new PropertyValueFactory<>("idioma"));
        paisJugador.setCellValueFactory(new PropertyValueFactory<>("pais"));
        preferidoJugador.setCellValueFactory(new PropertyValueFactory<>("juegoPreferido"));

    }

    public void MostrarId(MouseEvent mouseEvent) {
    }

    public void MostrarTodos(MouseEvent mouseEvent) {
    }


}
