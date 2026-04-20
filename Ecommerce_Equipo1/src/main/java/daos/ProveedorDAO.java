package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import models.Proveedor;
import util.JPAUtil;

public class ProveedorDAO implements IProveedorDAO {

    @Override
    public void guardar(Proveedor proveedor) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(proveedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Error al guardar proveedor", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Proveedor buscarPorId(int id) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Proveedor> listarTodos() {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Proveedor> query = em.createQuery("SELECT p FROM Proveedor p", Proveedor.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Proveedor proveedor) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(proveedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Error al actualizar proveedor", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(int id) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            Proveedor proveedor = em.find(Proveedor.class, id);
            if (proveedor != null) em.remove(proveedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Error al eliminar proveedor", e);
        } finally {
            em.close();
        }
    }
}
