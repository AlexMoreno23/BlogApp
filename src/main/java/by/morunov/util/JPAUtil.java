package by.morunov.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Alex Morunov
 */
public class JPAUtil {
    private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("JPAMyBlog");
    }

    public static EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

    public static void close(){
        entityManagerFactory.close();
    }
}
