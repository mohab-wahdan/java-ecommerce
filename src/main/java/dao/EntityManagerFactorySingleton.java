package dao;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactorySingleton {
    private static EntityManagerFactory entityManagerFactory;

    private EntityManagerFactorySingleton() {
        // Prevent instantiation
    }

    public static EntityManagerFactory getInstance() {
        if (entityManagerFactory == null) {
            synchronized (EntityManagerFactorySingleton.class) {
                if (entityManagerFactory == null) { // Double-check locking
                    entityManagerFactory = Persistence.createEntityManagerFactory("bookstore");
                }
            }
        }
        return entityManagerFactory;
    }
}
