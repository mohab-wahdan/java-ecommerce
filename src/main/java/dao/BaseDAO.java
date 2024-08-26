package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public abstract class BaseDAO<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> entityClass;

    public BaseDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    public void create(T entity) {
        entityManager.persist(entity);
    }

    public T read(Object id) {
        return entityManager.find(entityClass, id);
    }

    @Transactional
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Transactional
    public void delete(Object id) {
        T entity = read(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    public List<T> findAll() {
        return entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
    }
}
