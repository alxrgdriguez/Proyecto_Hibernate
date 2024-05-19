package com.jaroso.controladores;

import com.jaroso.entidades.Juego;
import com.jaroso.enums.Categoria;
import com.jaroso.enums.Plataforma;
import com.jaroso.repositorios.RepositorioJuego;
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

public class ControladorRegistroJuegos implements Initializable {

    //MOSTRAR
    @FXML
    private TableView<Juego> tablajuego;

    @FXML
    public TableColumn<Juego, Long> idJuego;

    public TableColumn<Juego, String> nombreJuego;

    public TableColumn<Juego, String> plataformaJuego;

    public TableColumn<Juego, Integer> pegiJuego;

    public TableColumn<Juego, String> categoriaJuego;

    @FXML
    public TextField campoIdBuscar;

    public Button mostrarTodo;

    public Button mostrarId;


    //INSERTAR
    public TextField tf_IdInsertar;

    public TextField tf_nombreInsertar;

    public ComboBox cbx_categoriaInsertar;

    public ComboBox cbx_plataformaInsertar;

    public TextField tf_pegiInsertar;

    public Button btn_insertar;

    public Text t_estadoInsert;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idJuego.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreJuego.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        plataformaJuego.setCellValueFactory(new PropertyValueFactory<>("plataforma"));
        pegiJuego.setCellValueFactory(new PropertyValueFactory<>("pegi"));
        categoriaJuego.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        mostrarId = new Button("Mostrar Id");
        mostrarTodo = new Button("Mostrar Todo");

        for (Categoria c : Categoria.values()) {
            cbx_categoriaInsertar.getItems().add(c);
        }

        for (Plataforma p : Plataforma.values()) {
            cbx_plataformaInsertar.getItems().add(p);
        }


    }


    public void MostrarId(MouseEvent mouseEvent) {
        RepositorioJuego rJuego = new RepositorioJuego();
        Long id = 0L;
        try {
            id = Long.parseLong(campoIdBuscar.getText());
            Juego juego = rJuego.findById(id);
            tablajuego.setItems(FXCollections.observableArrayList(juego));
        }catch (Exception e){
            System.out.println("No se encontro el juego con el id: " + id);
            tablajuego.setItems(FXCollections.observableArrayList());
        }

    }

    public void MostrarTodos(MouseEvent mouseEvent) {
        RepositorioJuego rJuego = new RepositorioJuego();
        List<Juego> juegos = rJuego.findAll();
        tablajuego.setItems(FXCollections.observableArrayList(juegos));
    }

    public void InsertarJuego(MouseEvent mouseEvent) {
        RepositorioJuego rJuego = new RepositorioJuego();
        System.out.println("asssssssssssssssssssssssssssssssssssssss");
        Long id = Long.parseLong(tf_IdInsertar.getText());
        String nombre = tf_nombreInsertar.getText();
        Plataforma plataforma = Plataforma.valueOf(cbx_plataformaInsertar.getValue().toString());
        Categoria categoria = Categoria.valueOf(cbx_categoriaInsertar.getValue().toString());

        Integer pegi = Integer.parseInt(tf_pegiInsertar.getText());

        Juego juego = new Juego(id, nombre, plataforma, pegi,  categoria);

        rJuego.insertarJuego(juego);
        t_estadoInsert.setText("Se ha insertado el juego correctamente");
        tf_IdInsertar.setText("");
        tf_nombreInsertar.setText("");
        cbx_plataformaInsertar.setValue("");
        cbx_categoriaInsertar.setValue("");
        tf_pegiInsertar.setText("");

    }
}
