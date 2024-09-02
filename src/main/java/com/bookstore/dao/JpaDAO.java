package com.bookstore.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class JpaDAO<E> {
    private static EntityManagerFactory entityManagerFactory;

    // Static to make it a Singleton
    static {
        entityManagerFactory =  Persistence.createEntityManagerFactory("omar");
    }

    public E create (E entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(entity);
        entityManager.flush();
        entityManager.refresh(entity);

        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    public E update (E entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();

        entityManager.close();
        return entity;
    }

    public E find(Class<E> entityClass, Object id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        E entity = entityManager.find(entityClass, id);
        entityManager.getTransaction().begin();
        if (entity != null) {
            entityManager.refresh(entity);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    public void delete (Class<E> entityClass, Object id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Object entity = entityManager.find(entityClass, id);
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public List<E> findWithNameQuery(String queryName){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery(queryName);
        List<E> result = query.getResultList();
        entityManager.close();
        return result;
    }

    public List<E> findWithNameQuery (String queryName, String paramName , Object paramValue){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery(queryName);
        query.setParameter(paramName, paramValue);
        List<E> result = query.getResultList();
        entityManager.close();
        return result;
    }

    public List<E> findWithNameQuery (String queryName , Map<String, Object> params ){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery(queryName);

        Set<Entry<String, Object>> entrySet = params.entrySet();

        for (Entry<String, Object> entry : entrySet) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        List<E> result = query.getResultList();
        entityManager.close();
        return result;
    }



    public void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
    }
