package com.jaroso.controladores;

import com.jaroso.entidades.Juego;
import com.jaroso.entidades.Jugador;
import com.jaroso.entidades.Partida;
import com.jaroso.enums.Categoria;
import com.jaroso.enums.Plataforma;
import com.jaroso.repositorios.RepositorioJuego;
import com.jaroso.repositorios.RepositorioJugador;
import com.jaroso.repositorios.RepositorioPartida;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorRegistroPartidas implements Initializable {

    //MOSTRAR
    @FXML
    public TableView<Partida> tablaPartidas;

    @FXML
    public TableColumn<Partida, Long> idPartida;

    public TableColumn<Partida, LocalDateTime> fechaHoraPartida;

    public TableColumn<Partida, Integer> duracionPartida;

    public TableColumn<Partida, Juego> juegoPartida;

    public TableColumn<Partida, Jugador> ganadorPartida;

    public TableColumn<Partida, List<Jugador>> participantesPartida;

    @FXML
    public TextField campoIdBuscar;

    public Button btn_buscar;

    //INSERTAR
    public DatePicker dp_fechaHoraInsertar;
    public TextField tf_duracionInsertar;
    public ComboBox cbx_juegoInsertar;
    public ComboBox cbx_ganadorInsertar;
    public ListView<String> lv_JugadoresInsertar;
    public Text t_estadoInsertar;


    //MODIFICAR
    public ComboBox cbx_idModificar;
    public DatePicker dp_fechaModificar;
    public TextField tf_duracionModificar;
    public ComboBox cbx_juegoModificar;
    public ComboBox cbx_ganadorModificar;
    public ListView<String> lv_participantesModificar;
    public Text t_estadoModificar;

    //ELIMINAR
    public TextField tf_idEliminar;
    public Button btn_eliminar;
    public Text t_estadoEliminar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idPartida.setCellValueFactory(new PropertyValueFactory<>("id"));
        fechaHoraPartida.setCellValueFactory(new PropertyValueFactory<>("fechaHora"));
        duracionPartida.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        juegoPartida.setCellValueFactory(new PropertyValueFactory<>("juego"));
        ganadorPartida.setCellValueFactory(new PropertyValueFactory<>("ganador"));
        participantesPartida.setCellValueFactory(new PropertyValueFactory<>("participantes"));

        lv_JugadoresInsertar.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lv_participantesModificar.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        RepositorioPartida rPartida = new RepositorioPartida();
        List<Partida> partidas = rPartida.findAll();

        tablaPartidas.setItems(FXCollections.observableArrayList(partidas));

        RepositorioJuego rJuego = new RepositorioJuego();
        List<Juego> juegos = rJuego.findAll();

        for (Juego j : juegos) {
            cbx_juegoInsertar.getItems().add(j.getNombre());
            cbx_juegoModificar.getItems().add(j.getNombre());
        }

        RepositorioJugador rJugador = new RepositorioJugador();
        List<Jugador> jugadores = rJugador.findAll();

        for (Jugador j : jugadores) {
            cbx_ganadorInsertar.getItems().add(j.getNick());
            lv_JugadoresInsertar.getItems().add(j.getNick());
            lv_participantesModificar.getItems().add(j.getNick());
            cbx_idModificar.getItems().add(j.getId());
            cbx_ganadorModificar.getItems().add(j.getNick());
        }
    }


    public void MostrarId(MouseEvent mouseEvent) {
        RepositorioPartida rPartida = new RepositorioPartida();
        Long id = 0L;
        try {
            id = Long.parseLong(campoIdBuscar.getText());
            Partida partida = rPartida.findById(id);
            tablaPartidas.setItems(FXCollections.observableArrayList(partida));

        }catch (Exception e){
            System.out.println("No se encontro la partida con el id: " + id);
            tablaPartidas.setItems(FXCollections.observableArrayList());
        }


    }

    public void MostrarTodos(MouseEvent mouseEvent) {
        RepositorioPartida rPartida = new RepositorioPartida();
        List<Partida> partidas = rPartida.findAll();

        tablaPartidas.setItems(FXCollections.observableArrayList(partidas));

    }



    public void MostrarInfoModifcar(ActionEvent actionEvent) {
        lv_participantesModificar.getSelectionModel().clearSelection();
        Long id = 0L;
        try {
            id = Long.parseLong(cbx_idModificar.getValue().toString());
        }catch (Exception e){
            return;
        }

        RepositorioPartida rPartida = new RepositorioPartida();
        Partida partida = rPartida.findById(id);

        dp_fechaModificar.setValue(partida.getFechaHora().toLocalDate());
        tf_duracionModificar.setText(partida.getDuracion().toString());
        cbx_juegoModificar.setValue(partida.getJuego().getNombre());
        cbx_ganadorModificar.setValue(partida.getGanador().getNick());

        for (Jugador j : partida.getParticipantes()) {
            lv_participantesModificar.getSelectionModel().select(j.getNick());
        }
    }




    public void InsertarPartida(MouseEvent mouseEvent) {
        RepositorioPartida rPartida = new RepositorioPartida();
        LocalTime ahora = LocalTime.now();
        LocalDateTime fechaHora = LocalDateTime.of(dp_fechaHoraInsertar.getValue(), ahora);
        Integer duracion = Integer.parseInt(tf_duracionInsertar.getText());
        String juegoNombre = cbx_juegoInsertar.getValue().toString();

        Juego juego = null;
        RepositorioJuego rJuego = new RepositorioJuego();
        List<Juego> juegos = rJuego.findAll();
        for (Juego juegoPartida : juegos) {
            if (juegoPartida.getNombre().equals(juegoNombre)) {
                juego = juegoPartida;
                break;
            }
        }

        String ganador = cbx_ganadorInsertar.getValue().toString();
        RepositorioJugador rJugador = new RepositorioJugador();
        List<Jugador> jugadores = rJugador.findAll();
        Jugador ganadorPartida = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getNick().equals(ganador)) {
                ganadorPartida = jugador;
                break;
            }
        }

        List<Jugador> participantes = new ArrayList<>();

        for (String nick : lv_JugadoresInsertar.getSelectionModel().getSelectedItems()) {
            for (Jugador jugador : jugadores) {
                if (jugador.getNick().equals(nick)) {
                    participantes.add(jugador);
                }
            }
        }

        Partida partida = new Partida(fechaHora, duracion, juego, ganadorPartida, participantes);
        rPartida.insertarPartida(partida);
        tf_duracionInsertar.setText("");
        duracionPartida.setText("");
        dp_fechaHoraInsertar.setValue(null);
        cbx_juegoInsertar.setValue("");
        cbx_ganadorInsertar.setValue("");
        t_estadoInsertar.setText("Se ha insertado la partida correctamente");
        cbx_idModificar.setValue("1");
    }


    public void modificarPartida(MouseEvent mouseEvent) {
        RepositorioPartida rPartida = new RepositorioPartida();
        RepositorioJuego rJuego = new RepositorioJuego();
        RepositorioJugador rJugador = new RepositorioJugador();
        Long id = Long.parseLong(cbx_idModificar.getValue().toString());
        LocalTime ahora = LocalTime.now();
        LocalDateTime fechaHora = LocalDateTime.of(dp_fechaModificar.getValue(), ahora);
        Integer duracion = Integer.parseInt(tf_duracionModificar.getText());
        String nombreJuego = cbx_juegoModificar.getValue().toString();
        Juego juego = null;
        for(Juego j : rJuego.findAll()){
            if(j.getNombre().equals(nombreJuego)){
                juego = j;
                break;
            }
        }

        String nickGanador = cbx_ganadorModificar.getValue().toString();
        Jugador ganadorPartida = null;
        for (Jugador jugador : rJugador.findAll()) {
            if (jugador.getNick().equals(nickGanador)) {
                ganadorPartida = jugador;
                break;
            }
        }

        List<Jugador> participantes = rJugador.findAll();
        List<Jugador> participantesModificar = new ArrayList<>();
        for (String nick : lv_participantesModificar.getSelectionModel().getSelectedItems()) {
            for (Jugador jugador : participantes) {
                if (jugador.getNick().equals(nick)) {
                    participantesModificar.add(jugador);
                }
            }
        }



        Partida partida = new Partida(fechaHora, duracion, juego, ganadorPartida, participantesModificar);

        partida.setId(id);

        rPartida.updatePartida(partida);
        t_estadoModificar.setText("Se ha modificado la partida correctamente");
        cbx_idModificar.setValue("");
        tf_duracionModificar.setText("");
        dp_fechaModificar.setValue(null);
        cbx_juegoModificar.setValue("");
        cbx_ganadorModificar.setValue("");
    }

    /**
     * Eliminar una partida por id
     * @param mouseEvent
     */
    public void eliminarPartida(MouseEvent mouseEvent) {
        RepositorioPartida rPartida = new RepositorioPartida();
        Long id = 0L;
        id = Long.parseLong(tf_idEliminar.getText());
        Partida partida = rPartida.findById(id);
        rPartida.eliminarPartida(partida);
        t_estadoEliminar.setText("Se ha eliminado la partida correctamente");
        tf_idEliminar.setText("");
    }
}
