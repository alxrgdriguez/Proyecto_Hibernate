package com.jaroso.controladores;

import com.jaroso.entidades.Juego;
import com.jaroso.repositorios.RepositorioJuego;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorJuego implements Initializable {

    @FXML
    private TableView<Juego> tablajuego;

    @FXML
    public TableColumn<Juego, Long> idJuego;

    public TableColumn<Juego, String> nombreJuego;

    public TableColumn<Juego, String> plataformaJuego;

    public TableColumn<Juego, Integer> pegiJuego;

    public TableColumn<Juego, String> categoriaJuego;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idJuego.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreJuego.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        plataformaJuego.setCellValueFactory(new PropertyValueFactory<>("plataforma"));
        pegiJuego.setCellValueFactory(new PropertyValueFactory<>("pegi"));
        categoriaJuego.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        RepositorioJuego rJuego = new RepositorioJuego();
        List<Juego> juegos = rJuego.findAll();
        tablajuego.setItems(FXCollections.observableArrayList(juegos));

    }
}
