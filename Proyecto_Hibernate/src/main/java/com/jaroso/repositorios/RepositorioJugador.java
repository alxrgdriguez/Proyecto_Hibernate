package com.jaroso.repositorios;

import com.jaroso.HibernateUtil;
import com.jaroso.entidades.Juego;
import com.jaroso.entidades.Jugador;
import org.hibernate.Session;

import java.util.List;

public class RepositorioJugador {

    private Session session;

    /**
     * Crear session en hibernate
     */
    public RepositorioJugador(){
        session = HibernateUtil.getSessionFactory().openSession();
    }

    /**
     * Cerrar session en hibernate
     */
    public void cerrarSession(){
        session.close();
    }

    /**
     * Inserta un objeto Jugador en la BBDD
     * @param jugador (no lleve id, sino hace update)
     */
    public void insertarJugador(Jugador jugador){
        session.beginTransaction();
        session.persist(jugador);
        session.getTransaction().commit();
    }

    /**
     * Busca la primary key
     */
    public Jugador findById(Long id){
        var Jugador = session.find(com.jaroso.entidades.Jugador.class, id);
        return Jugador;
    }

    /**
     * Eliminar por nick
     */
    public Jugador deleteByNick(String nick){
        // Normalizamos el nombre: Convertimos a minúsculas y eliminamos espacios en blanco
        String nickNormalizado = nick.toLowerCase().replace(" ", "");

        // Creamos la consulta utilizando funciones SQL para normalizar el nombre en la base de datos
        return session.createQuery(
                        "Select jugador From Jugador jugador Where " +
                                "LOWER(REPLACE(jugador.nick, ' ', '')) = :nick", Jugador.class)
                .setParameter("nick", nickNormalizado)
                .uniqueResult();
    }

    /**
     * Elimina un objeto jugador en la base de datos
     */
    public void eliminarJugador(Jugador jugador){
        session.beginTransaction();
        session.remove(jugador);
        session.getTransaction().commit();
    }

    /**
     * Busca todos los objetos Jugador
     * @return List con todos los Jugadores en la base de datos
     */
    public List<Jugador> findAll(){
        return session.createQuery("Select jugador From Jugador jugador", Jugador.class).list();
    }

    /**
     * Actualiza un objeto Jugador en la BBDD
     * @param jugador
     */
    public void updateJugador(Jugador jugador){
        session.beginTransaction();
        session.update(jugador);
        session.getTransaction().commit();
    }
}
