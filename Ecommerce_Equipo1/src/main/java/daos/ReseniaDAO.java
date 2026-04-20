/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import jakarta.persistence.EntityManager;
import models.Resenia;
import util.JPAUtil;

/**
 *
 * @author Adrián
 */
public class ReseniaDAO implements IReseniaDAO {

    EntityManager em = JPAUtil.getInstance().getEntityManager();

    @Override
    public void guardar(Resenia resenia) {
        em.getTransaction().begin();
        em.persist(resenia);
        em.getTransaction().commit();
    }

    @Override
    public Resenia buscarPorId(int id) {
        return em.find(Resenia.class, id);
    }

    @Override
    public void actualizar(Resenia resenia) {
        em.getTransaction().begin();
        em.merge(resenia);
        em.getTransaction().commit();
    }

    @Override
    public int eliminar(int id) {
        em.getTransaction().begin();

        Resenia resenia = em.find(Resenia.class, id);

        em.remove(resenia);

        em.getTransaction().commit();
        return id;
    }

}
