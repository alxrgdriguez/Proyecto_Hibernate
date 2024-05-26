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
    public void insertarJuego(Juego juego){
        session.beginTransaction();
        session.persist(juego);
        session.getTransaction().commit();
    }

    /**
     * Busca la primary key
     */
    public Juego findById(Long id){
        var Juego = session.find(com.jaroso.entidades.Juego.class, id);
        return Juego;
    }

    /**
     * Eliminar por nombre
     */
    public Juego deleteByNombre(String nombre){
        // Normalizamos el nombre: Convertimos a minúsculas y eliminamos espacios en blanco
        String nombreNormalizado = nombre.toLowerCase().replace(" ", "");

        // Creamos la consulta utilizando funciones SQL para normalizar el nombre en la base de datos
        return session.createQuery(
                        "Select juego From Juego juego Where " +
                                "LOWER(REPLACE(juego.nombre, ' ', '')) = :nombre", Juego.class)
                .setParameter("nombre", nombreNormalizado)
                .uniqueResult();
    }

    /**
     * Elimina un objeto jugador en la base de datos
     */
    public void eliminarJuego(Juego juego){
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
