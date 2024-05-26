package com.jaroso.controladores;

import com.jaroso.entidades.Juego;
import com.jaroso.entidades.Jugador;
import com.jaroso.enums.Categoria;
import com.jaroso.enums.Plataforma;
import com.jaroso.repositorios.RepositorioJuego;
import com.jaroso.repositorios.RepositorioJugador;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
    public TableView<Jugador> tablaJugadores;

    @FXML
    public TableColumn<Jugador, Long> idJugador;

    public TableColumn<Jugador, String> nickJugador;

    public TableColumn<Jugador, Integer> edadJugador;

    public TableColumn<Jugador, String> emailJugador;

    public TableColumn<Jugador, String> idiomaJugador;

    public TableColumn<Jugador, String> paisJugador;

    public TableColumn<Jugador, Juego> juegoPreferido;

    public Button btn_buscar;

    public Button btn_mostrarTodo;

    public TextField campoIdBuscar;

    //INSERTAR
    public TextField tf_nickInsertar;

    public TextField tf_edadInsertar;

    public TextField tf_emailInsertar;

    public TextField tf_idiomaInsertar;

    public TextField tf_paisInsertar;

    public ComboBox cbx_jpreferidoInsertar;

    public Text t_estadoInsertar;


    //ELIMINAR
    public TextField tf_nickEliminar;

    public Button btn_eliminar;

    public Text t_estadoEliminar;

    //MODIFICAR
    public ComboBox cbx_idModificar;

    public TextField tf_nickModificar;

    public TextField tf_edadModificar;

    public TextField tf_emailModificar;

    public TextField tf_idiomaModificar;

    public TextField tf_paisModificar;

    public ComboBox cbx_jpreferidoModificar;

    public Text t_estadoModificar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idJugador.setCellValueFactory(new PropertyValueFactory<>("id"));
        nickJugador.setCellValueFactory(new PropertyValueFactory<>("nick"));
        edadJugador.setCellValueFactory(new PropertyValueFactory<>("edad"));
        emailJugador.setCellValueFactory(new PropertyValueFactory<>("email"));
        idiomaJugador.setCellValueFactory(new PropertyValueFactory<>("idioma"));
        paisJugador.setCellValueFactory(new PropertyValueFactory<>("pais"));
        juegoPreferido.setCellValueFactory(new PropertyValueFactory<>("juegoPreferido"));

        RepositorioJugador rJugador = new RepositorioJugador();
        List<Jugador> jugadores = rJugador.findAll();

        tablaJugadores.setItems(FXCollections.observableArrayList(jugadores));


        RepositorioJuego rJuego = new RepositorioJuego();
        List<Juego> juegos = rJuego.findAll();


        for (Juego j : juegos) {
            cbx_jpreferidoInsertar.getItems().add(j.getNombre());
            cbx_jpreferidoModificar.getItems().add(j.getNombre());
        }

        for (Jugador j : jugadores) {
            cbx_idModificar.getItems().add(j.getId());

        }
    }

    public void MostrarTodos(MouseEvent mouseEvent) {

        RepositorioJugador rJugador = new RepositorioJugador();
        List<Jugador> jugadores = rJugador.findAll();

        tablaJugadores.setItems(FXCollections.observableArrayList(jugadores));

    }

    public void MostrarId(MouseEvent mouseEvent) {
        RepositorioJugador rJugador = new RepositorioJugador();
        Long id = 0L;
        try {
            id = Long.parseLong(campoIdBuscar.getText());
            Jugador jugador = rJugador.findById(id);
            tablaJugadores.setItems(FXCollections.observableArrayList(jugador));

        }catch (Exception e){
            System.out.println("No se encontro el jugador con el id: " + id);
            tablaJugadores.setItems(FXCollections.observableArrayList());
        }
    }

    public void InsertarJugador(MouseEvent mouseEvent) {

        RepositorioJugador rJugador = new RepositorioJugador();
        String nick = tf_nickInsertar.getText();
        String email = tf_emailInsertar.getText();
        Integer edad = Integer.parseInt(tf_edadInsertar.getText());
        String idioma = tf_idiomaInsertar.getText();
        String pais = tf_paisInsertar.getText();

        Juego juegoPreferido =null;
        RepositorioJuego rJuego = new RepositorioJuego();
        List<Juego> juegos = rJuego.findAll();
        for (Juego juego : juegos) {
            if (juego.getNombre().equals(cbx_jpreferidoInsertar.getValue().toString())) {
                juegoPreferido = juego;
                break;
            }
        }


        Jugador jugador = new Jugador(nick, email, edad, idioma, pais, juegoPreferido);
        rJugador.insertarJugador(jugador);
        t_estadoInsertar.setText("Se ha insertado el jugador correctamente");
        tf_nickInsertar.setText("");
        tf_edadInsertar.setText("");
        tf_emailInsertar.setText("");
        tf_idiomaInsertar.setText("");
        tf_paisInsertar.setText("");
        cbx_jpreferidoInsertar.setValue("");

    }

    public void modificarJugador(MouseEvent mouseEvent) {
        Long id = Long.parseLong(cbx_idModificar.getValue().toString());
        String nick = tf_nickModificar.getText();
        String email = tf_emailModificar.getText();
        Integer edad = Integer.parseInt(tf_edadModificar.getText());
        String idioma = tf_idiomaModificar.getText();
        String pais = tf_paisModificar.getText();
        Juego juegoPreferido = null;

        String nombreJuego = cbx_jpreferidoModificar.getValue().toString();
        RepositorioJuego rJuego = new RepositorioJuego();
        List<Juego> juegos = rJuego.findAll();
        for (Juego juego : juegos) {
            if (juego.getNombre().equals(nombreJuego)) {
                juegoPreferido = juego;
                break;
            }
        }


        Jugador jugador = new Jugador(nick, email, edad, idioma, pais, juegoPreferido);
        RepositorioJugador rJugador = new RepositorioJugador();
        jugador.setId(id);

        rJugador.updateJugador(jugador);
        t_estadoModificar.setText("Se ha modificado el jugador correctamente");
        cbx_idModificar.setValue(jugador.getId().toString());
        tf_nickModificar.setText("");
        tf_edadModificar.setText("");
        tf_emailModificar.setText("");
        tf_idiomaModificar.setText("");
        tf_paisModificar.setText("");
        cbx_jpreferidoModificar.setValue("");
    }

    public void MostrarInfoModifcar(ActionEvent actionEvent) {
        Long id = Long.parseLong(cbx_idModificar.getValue().toString());
        RepositorioJugador rJugador = new RepositorioJugador();
        Jugador jugador = rJugador.findById(id);

        tf_nickModificar.setText(jugador.getNick());
        tf_edadModificar.setText(jugador.getEdad().toString());
        tf_emailModificar.setText(jugador.getEmail());
        tf_idiomaModificar.setText(jugador.getIdioma());
        tf_paisModificar.setText(jugador.getPais());
        cbx_jpreferidoModificar.setValue(jugador.getJuegoPreferido().getNombre());
    }

    public void eliminarJugador(MouseEvent mouseEvent) {

        RepositorioJugador rJugador = new RepositorioJugador();
        String nick = tf_nickEliminar.getText();
        Jugador jugador = rJugador.deleteByNick(nick);
        rJugador.eliminarJugador(jugador);
        t_estadoEliminar.setText("Se ha eliminado el jugador correctamente");
        tf_nickEliminar.setText("");
    }
}
