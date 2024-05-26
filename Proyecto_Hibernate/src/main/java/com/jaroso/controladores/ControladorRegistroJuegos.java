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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    public Button btn_buscar;

    public Button mostrarTodo;

    public Button mostrarId;


    //INSERTAR

    public TextField tf_nombreInsertar;

    public ComboBox cbx_categoriaInsertar;

    public ComboBox cbx_plataformaInsertar;

    public TextField tf_pegiInsertar;

    public Button btn_insertar;

    public Text t_estadoInsert;

    //MODIFICAR
    @FXML
    public TableView<Juego> tv_modificarJuegos;

    public TableColumn<Juego, Long> idJuegoModificar;

    public TableColumn<Juego, String> nombreJuegoModificar;

    public TableColumn<Juego, Integer> pegiJuegoModificar;

    public TableColumn<Juego, String> plataformaJuegoModificar;

    public TableColumn<Juego, String> categoriaJuegoModificar;


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
        cbx_categoriaInsertar.setValue(Categoria.ACCION);

        for (Plataforma p : Plataforma.values()) {
            cbx_plataformaInsertar.getItems().add(p);
        }
        cbx_plataformaInsertar.setValue(Plataforma.PC);

        RepositorioJuego rJuego = new RepositorioJuego();
        List<Juego> juegos = rJuego.findAll();

        tablajuego.setItems(FXCollections.observableArrayList(juegos));
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


        tv_modificarJuegos.setItems(FXCollections.observableArrayList(juegos));
    }

    public void InsertarJuego(MouseEvent mouseEvent) {
        RepositorioJuego rJuego = new RepositorioJuego();
        String nombre = tf_nombreInsertar.getText();
        Plataforma plataforma = Plataforma.valueOf(cbx_plataformaInsertar.getValue().toString());
        Categoria categoria = Categoria.valueOf(cbx_categoriaInsertar.getValue().toString());

        Integer pegi = Integer.parseInt(tf_pegiInsertar.getText());

        //Juego juego = new Juego(id, nombre, plataforma, pegi,  categoria);
        Juego juego = new Juego();
        juego.setNombre(nombre);
        juego.setPlataforma(plataforma);
        juego.setCategoria(categoria);
        juego.setPegi(pegi);

        rJuego.insertarJuego(juego);
        t_estadoInsert.setText("Se ha insertado el juego correctamente");
        tf_nombreInsertar.setText("");
        cbx_plataformaInsertar.setValue(Plataforma.PC);
        cbx_categoriaInsertar.setValue(Categoria.ACCION);
        tf_pegiInsertar.setText("");

    }


    //ELIMINAR POR NOMBRE
    @FXML
    public TextField tf_nombreEliminar;
    public Button btn_eliminar;
    public Text t_estadoEliminar;

    public void eliminarJuego(MouseEvent mouseEvent) {
        RepositorioJuego rJuego = new RepositorioJuego();
        String nombre = tf_nombreEliminar.getText();
        Juego juego = rJuego.deleteByNombre(nombre);
        rJuego.eliminarJuego(juego);
        t_estadoEliminar.setText("Se ha eliminado el juego correctamente");
        tf_nombreEliminar.setText("");

    }



    public void ActualizarJuego(KeyEvent keyEvent) {

        if( keyEvent.getCode() == KeyCode.ENTER ) {
            System.out.println("ENTER");
        }



    }
}
