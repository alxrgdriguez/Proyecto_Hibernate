package com.jaroso.repositorios;

import com.jaroso.HibernateUtil;
import com.jaroso.entidades.Juego;
import org.hibernate.Session;

import java.util.List;

public class RepositorioJuego {

    private Session session;

    /**
     * Crear session en hibernate
     */
    public RepositorioJuego(){
        session = HibernateUtil.getSessionFactory().openSession();
    }

    /**
     * Cerrar session en hibernate
     */
    public void cerrarSession(){
        session.close();
    }

    /**
     * Inserta un objeto Juego en la BBDD
     * @param juego (no lleve id, sino hace update)
     */
    public void insertarJugador(Juego juego){
        session.beginTransaction();
        session.persist(juego);
        session.getTransaction().commit();
    }

    /**
     * Busca la primary key
     */
    public Juego findById(Long id){
        var Juego = session.find(com.jaroso.entidades.Jugador.class, id);
        return Juego.getJuegoPreferido();
    }

    /**
     * Elimina un objeto jugador en la base de datos
     */
    public void eliminarJugador(Juego juego){
        session.beginTransaction();
        session.remove(juego);
        session.getTransaction().commit();
    }

    /**
     * Busca todos los objetos Jugador
     * @return List con todos los Jugadores en la base de datos
     */
    public List<Juego> findAll(){
        return session.createQuery("Select juego From Juego juego", Juego.class).list();
    }
}
