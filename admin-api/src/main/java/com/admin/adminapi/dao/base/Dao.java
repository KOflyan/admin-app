package com.admin.adminapi.dao.base;

import com.admin.adminapi.utils.Utils;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public abstract class Dao<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    private String className;
    private Class<T> clazz;

    public Dao() {
        clazz = Utils.resolveClassOfT(getClass(), Dao.class);
        className = Utils.getClassName(clazz);
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        String query = String.format("SELECT t from %s t", className);
        return entityManager.createQuery(query).getResultList();
    }

    @SuppressWarnings("unchecked")
    public T getById(int id) {
        return entityManager.find(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public void delete(int id) {
        String query = String.format("SELECT t FROM %s t WHERE id= :id", className);
        entityManager.createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }

    public void update(T t) {
        entityManager.refresh(t);
    }

    public void create(T t) {
        entityManager.persist(t);
    }
}
