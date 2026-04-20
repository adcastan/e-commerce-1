/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import jakarta.persistence.EntityManager;
import java.util.List;
import models.Libro;
import util.JPAUtil;

/**
 *
 * @author Adrián
 */
public class LibroDAO implements ILibroDAO {

    EntityManager em = JPAUtil.getInstance().getEntityManager();

    @Override
    public void guardar(Libro libro) {
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
    }

    @Override
    public Libro buscarPorGenero(String genero) {
        return em.find(Libro.class, genero);
    }

    @Override
    public void actualizar(Libro libro) {
        em.getTransaction().begin();
        em.merge(libro);
        em.getTransaction().commit();
    }

    @Override
    public void eliminar(int id) {
        em.getTransaction().begin();

        Libro libro = em.find(Libro.class, id);

        em.remove(libro);

        em.getTransaction().commit();
    }

}
