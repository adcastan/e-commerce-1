package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static JPAUtil instance;
    private final EntityManagerFactory emf;

    private JPAUtil() {
        try {
            emf = Persistence.createEntityManagerFactory("ecommerce-1");
        } catch (Exception e) {
            throw new RuntimeException("error al crear EntityManagerFactory", e);
        }
    }

    public static synchronized JPAUtil getInstance() {
        if (instance == null) {
            instance = new JPAUtil();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
