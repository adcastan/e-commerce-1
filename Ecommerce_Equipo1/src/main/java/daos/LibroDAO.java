package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import models.Libro;
import util.JPAUtil;

public class LibroDAO implements ILibroDAO {

    @Override
    public Libro guardar(Libro libro) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
            return libro;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Libro buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.find(Libro.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Libro buscarPorIsbn(String isbn) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Libro> q = em.createQuery(
                    "SELECT l FROM Libro l WHERE l.isbn = :isbn", Libro.class);
            q.setParameter("isbn", isbn);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Libro> listar() {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.createQuery("SELECT l FROM Libro l ORDER BY l.idLibro DESC", Libro.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Libro> listarActivos() {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.createQuery(
                    "SELECT l FROM Libro l WHERE l.activo = true ORDER BY l.titulo", Libro.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Libro> listarDestacados(int limite) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.createQuery(
                    "SELECT l FROM Libro l WHERE l.activo = true AND l.destacado = true ORDER BY l.idLibro DESC", Libro.class)
                    .setMaxResults(limite)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Libro> listarRecientes(int limite) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.createQuery(
                    "SELECT l FROM Libro l WHERE l.activo = true ORDER BY l.idLibro DESC", Libro.class)
                    .setMaxResults(limite)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Libro> filtrar(String texto, Integer idGenero, Double precioMin, Double precioMax) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Libro> cq = cb.createQuery(Libro.class);
            Root<Libro> raiz = cq.from(Libro.class);

            List<Predicate> predicados = new ArrayList<>();
            predicados.add(cb.isTrue(raiz.get("activo")));

            if (texto != null && !texto.isBlank()) {
                String like = "%" + texto.toLowerCase() + "%";
                Predicate porTitulo = cb.like(cb.lower(raiz.get("titulo")), like);
                Predicate porAutor = cb.like(cb.lower(raiz.get("autor")), like);
                Predicate porIsbn = cb.like(cb.lower(raiz.get("isbn")), like);
                predicados.add(cb.or(porTitulo, porAutor, porIsbn));
            }
            if (idGenero != null) {
                predicados.add(cb.equal(raiz.get("genero").get("idGenero"), idGenero));
            }
            if (precioMin != null) {
                predicados.add(cb.greaterThanOrEqualTo(raiz.get("precio"), precioMin));
            }
            if (precioMax != null) {
                predicados.add(cb.lessThanOrEqualTo(raiz.get("precio"), precioMax));
            }

            cq.select(raiz)
                    .where(predicados.toArray(new Predicate[0]))
                    .orderBy(cb.asc(raiz.get("titulo")));

            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Libro actualizar(Libro libro) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            Libro actualizado = em.merge(libro);
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
            Libro l = em.find(Libro.class, id);
            if (l == null) {
                em.getTransaction().rollback();
                return false;
            }
            l.setActivo(false);
            em.merge(l);
            em.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean descontarStock(Integer idLibro, int cantidad) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            Libro l = em.find(Libro.class, idLibro);
            if (l == null || l.getStock() < cantidad) {
                em.getTransaction().rollback();
                return false;
            }
            l.setStock(l.getStock() - cantidad);
            em.merge(l);
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
