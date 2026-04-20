/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import jakarta.persistence.EntityManager;
import models.Venta;
import util.JPAUtil;

/**
 *
 * @author Adrián
 */
public class VentaDAO implements IVentaDAO {

    EntityManager em = JPAUtil.getInstance().getEntityManager();

    @Override
    public void guardar(Venta venta) {
        em.getTransaction().begin();
        em.persist(venta);
        em.getTransaction().commit();
    }

    @Override
    public Venta buscarVentaPorId(int id) {
        return em.find(Venta.class, id);
    }

    @Override
    public void actualizarVenta(Venta venta) {
        em.getTransaction().begin();
        em.merge(venta);
        em.getTransaction().commit();
    }

    @Override
    public int eliminar(int id) {
 em.getTransaction().begin();

        Venta venta = em.find(Venta.class, id);

        em.remove(venta);

        em.getTransaction().commit();
        return id;   
    }

}
