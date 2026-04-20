/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.Libro;
import util.JPAUtil;

/**
 *
 * @author Adrián
 */
public class LibroDAO implements ILibroDAO {

    @Override
    public void guardar(Libro libro) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Error al guardar libro", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Libro buscarPorId(int id) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.find(Libro.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Libro> listarTodos() {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Libro> query = em.createQuery(
                "SELECT l FROM Libro l LEFT JOIN FETCH l.genero LEFT JOIN FETCH l.proveedor", Libro.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Libro> listarPorGenero(int idGenero) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Libro> query = em.createQuery(
                "SELECT l FROM Libro l WHERE l.genero.idGenero = :idGenero", Libro.class);
            query.setParameter("idGenero", idGenero);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Libro> buscarPorTitulo(String titulo) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Libro> query = em.createQuery(
                "SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE LOWER(:titulo)", Libro.class);
            query.setParameter("titulo", "%" + titulo + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Libro libro) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Error al actualizar libro", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(int id) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            Libro libro = em.find(Libro.class, id);
            if (libro != null) em.remove(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Error al eliminar libro", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Libro crearLibro(Libro libro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Libro buscarPorCorreoYContrasenia(String titulo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Libro buscarPorGenero(String genero) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
}
