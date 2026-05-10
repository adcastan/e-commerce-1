package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import models.Genero;
import util.JPAUtil;

public class GeneroDAO implements IGeneroDAO {

    @Override
    public Genero guardar(Genero genero) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(genero);
            em.getTransaction().commit();
            return genero;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Genero buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.find(Genero.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Genero buscarPorNombre(String nombre) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Genero> q = em.createQuery(
                    "SELECT g FROM Genero g WHERE g.nombre = :nombre", Genero.class);
            q.setParameter("nombre", nombre);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Genero> listar() {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.createQuery("SELECT g FROM Genero g ORDER BY g.nombre", Genero.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Genero actualizar(Genero genero) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            Genero actualizado = em.merge(genero);
            em.getTransaction().commit();
            return actualizado;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean eliminar(Integer id) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            Genero g = em.find(Genero.class, id);
            if (g == null) {
                em.getTransaction().rollback();
                return false;
            }
            em.remove(g);
            em.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
