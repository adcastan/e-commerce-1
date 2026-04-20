package daos;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.Genero;
import util.JPAUtil;

public class GeneroDAO implements IGeneroDAO {

    @Override
    public void guardar(Genero genero) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(genero);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Error al guardar género", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Genero buscarPorId(int id) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.find(Genero.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Genero> listarTodos() {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Genero> query = em.createQuery("SELECT g FROM Genero g", Genero.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Genero genero) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(genero);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Error al actualizar género", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(int id) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            Genero genero = em.find(Genero.class, id);
            if (genero != null) em.remove(genero);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Error al eliminar género", e);
        } finally {
            em.close();
        }
    }
}
