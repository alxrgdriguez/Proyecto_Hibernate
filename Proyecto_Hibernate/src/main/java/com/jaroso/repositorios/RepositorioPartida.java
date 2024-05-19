package com.jaroso.repositorios;

import com.jaroso.HibernateUtil;
import com.jaroso.entidades.Partida;
import org.hibernate.Session;

import java.util.List;

public class RepositorioPartida {

    private Session session;

    /**
     * Crear session en hibernate
     */
    public RepositorioPartida(){
        session = HibernateUtil.getSessionFactory().openSession();
    }

    /**
     * Cerrar session en hibernate
     */
    public void cerrarSession(){
        session.close();
    }

    /**
     * Inserta un objeto Partida en la BBDD
     * @param partida (no lleve id, sino hace update)
     */
    public void insertarPartida(Partida partida){
        session.beginTransaction();
        session.save(partida);
        //session.persist(partida); Revisar si es necesario
        session.getTransaction().commit();
    }

    /**
     * Buscar la primary key
     */
    public Partida findById(Long id){
        return session.find(com.jaroso.entidades.Partida.class, id);
    }

    /**
     * Elimina un objeto Partida en la base de datos
     */
    public void eliminarPartida(Partida partida){
        session.beginTransaction();
        session.remove(partida);
        session.getTransaction().commit();
    }

    /**
     * Busca todos los objetos Partida
     * @return List con todos los Jugadores en la base de datos
     */
    public List<Partida> findAll(){
        return session.createQuery("Select partida From Partida partida", Partida.class).list();
    }
}

