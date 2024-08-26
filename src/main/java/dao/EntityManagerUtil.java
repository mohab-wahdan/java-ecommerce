package dao;
import jakarta.persistence.EntityManager;

public class EntityManagerUtil {
    public static EntityManager getEntityManager() {
        return EntityManagerFactorySingleton.getInstance().createEntityManager();
    }
}
