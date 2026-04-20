/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import jakarta.persistence.EntityManager;
import java.util.List;
import models.Cliente;
import util.JPAUtil;

/**
 *
 * @author Adrián
 */
public class ClienteDAO implements IClienteDAO {

    EntityManager em = JPAUtil.getInstance().getEntityManager();

    @Override
    public void guardar(Cliente cliente) {
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();

    }

    @Override
    public void actualizar(Cliente cliente) {

        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();

    }

    @Override
    public int eliminar(int id) {

        em.getTransaction().begin();

        Cliente cliente = em.find(Cliente.class, id);

        em.remove(cliente);

        em.getTransaction().commit();
        return id;

    }

    @Override
    public Cliente buscarPorId(int id) {
        return em.find(Cliente.class, id);
    }


}
