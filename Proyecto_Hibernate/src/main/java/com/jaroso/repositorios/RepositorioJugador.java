package com.jaroso.repositorios;

import com.jaroso.HibernateUtil;
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
}
