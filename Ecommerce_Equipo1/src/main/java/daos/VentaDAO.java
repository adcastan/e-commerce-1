package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import models.EstadoVenta;
import models.Venta;
import util.JPAUtil;

public class VentaDAO implements IVentaDAO {

    @Override
    public Venta guardar(Venta venta) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(venta);
            em.getTransaction().commit();
            return venta;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Venta buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Venta buscarPorNumeroPedido(String numero) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Venta> q = em.createQuery(
                    "SELECT v FROM Venta v WHERE v.numeroPedido = :n", Venta.class);
            q.setParameter("n", numero);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Venta> listar() {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.createQuery(
                    "SELECT v FROM Venta v ORDER BY v.fechaHora DESC", Venta.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Venta> listarPorCliente(Integer idCliente) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Venta> q = em.createQuery(
                    "SELECT v FROM Venta v WHERE v.cliente.idCliente = :id ORDER BY v.fechaHora DESC", Venta.class);
            q.setParameter("id", idCliente);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Venta> listarPorEstado(EstadoVenta estado) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Venta> q = em.createQuery(
                    "SELECT v FROM Venta v WHERE v.estado = :e ORDER BY v.fechaHora DESC", Venta.class);
            q.setParameter("e", estado);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Venta actualizarEstado(Integer idVenta, EstadoVenta nuevoEstado) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            Venta v = em.find(Venta.class, idVenta);
            if (v == null) {
                em.getTransaction().rollback();
                return null;
            }
            v.setEstado(nuevoEstado);
            Venta actualizado = em.merge(v);
            em.getTransaction().commit();
            return actualizado;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
