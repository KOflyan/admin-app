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
    private EntityManager entityManager;

    private String className;
    private Class<T> clazz;

    @SuppressWarnings("unchecked")
    public Dao() {
//        clazz = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), Dao.class);
        clazz = Utils.resolveClassOfT(getClass(), Dao.class);
        className = Utils.getClassName(clazz);
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        String query = String.format("SELECT * FROM `%s`", className);
        return executeQuery(query).getResultList();
    }

    @SuppressWarnings("unchecked")
    public T getById(int id) {
        String query = String.format("SELECT * FROM `%s` WHERE id=%s", className, id);
        return (T) executeQuery(query).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public void delete(int id) {
        String query = String.format("DELETE FROM `%s` WHERE id=%s", className, id);
        executeQuery(query);
    }

    public void update(T t) {
        // TODO
    }

    public void create(T t) {
        // TODO
    }

    private Query executeQuery(String query) {
        return entityManager.createNativeQuery(query, clazz);
    }
}
