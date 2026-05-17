package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import models.Cliente;
import util.JPAUtil;

public class ClienteDAO implements IClienteDAO {

    @Override
    public Cliente guardar(Cliente cliente) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            return cliente;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Cliente buscarPorCorreo(String correo) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Cliente> q = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.correo = :correo", Cliente.class);
            q.setParameter("correo", correo);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Cliente> listar() {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.createQuery(
                    "SELECT c FROM Cliente c ORDER BY c.idCliente", Cliente.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Cliente actualizar(Cliente cliente) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente actualizado = em.merge(cliente);
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
            Cliente c = em.find(Cliente.class, id);
            if (c == null) {
                em.getTransaction().rollback();
                return false;
            }
            em.remove(c);
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
