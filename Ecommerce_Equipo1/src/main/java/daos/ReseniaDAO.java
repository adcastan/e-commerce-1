package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import models.Resenia;
import util.JPAUtil;

public class ReseniaDAO implements IReseniaDAO {

    @Override
    public Resenia guardar(Resenia resenia) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(resenia);
            em.getTransaction().commit();
            return resenia;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Resenia buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.find(Resenia.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Resenia> listar() {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.createQuery(
                    "SELECT r FROM Resenia r ORDER BY r.fechaHora DESC", Resenia.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Resenia> listarPorLibro(Integer idLibro) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Resenia> q = em.createQuery(
                    "SELECT r FROM Resenia r WHERE r.libro.idLibro = :id ORDER BY r.fechaHora DESC", Resenia.class);
            q.setParameter("id", idLibro);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Resenia> listarPorCliente(Integer idCliente) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Resenia> q = em.createQuery(
                    "SELECT r FROM Resenia r WHERE r.cliente.idCliente = :id ORDER BY r.fechaHora DESC", Resenia.class);
            q.setParameter("id", idCliente);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Double calificacionPromedio(Integer idLibro) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Double> q = em.createQuery(
                    "SELECT AVG(r.calificacion) FROM Resenia r WHERE r.libro.idLibro = :id", Double.class);
            q.setParameter("id", idLibro);
            Double res = q.getSingleResult();
            return res == null ? 0.0 : res;
        } catch (NoResultException e) {
            return 0.0;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean eliminar(Integer id) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            Resenia r = em.find(Resenia.class, id);
            if (r == null) {
                em.getTransaction().rollback();
                return false;
            }
            em.remove(r);
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
