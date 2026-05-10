package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import models.MetodoPago;
import util.JPAUtil;

public class MetodoPagoDAO implements IMetodoPagoDAO {

    @Override
    public MetodoPago guardar(MetodoPago metodoPago) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(metodoPago);
            em.getTransaction().commit();
            return metodoPago;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public MetodoPago buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.find(MetodoPago.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public MetodoPago buscarPorTipo(String tipo) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<MetodoPago> q = em.createQuery(
                    "SELECT m FROM MetodoPago m WHERE m.tipo = :tipo", MetodoPago.class);
            q.setParameter("tipo", tipo);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<MetodoPago> listar() {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.createQuery("SELECT m FROM MetodoPago m ORDER BY m.idPago", MetodoPago.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
