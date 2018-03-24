package com.admin.adminapi.base.dao;

import com.admin.adminapi.impl.dao.entities.Device;
import com.admin.adminapi.utils.Utils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public abstract class Dao<T> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> clazz;
    private String className;

    public Dao() {
        clazz = Utils.resolveClassOfT(getClass(), Dao.class);
        className = Utils.getClassName(clazz);
    }

    public List<T> getAll() {
        String query = String.format("SELECT t FROM %s t LIMIT", className);

        return em.createQuery(query, clazz)
                .getResultList();
    }

    public List<T> getAll(int skip, int limit) {

        String query = String.format("SELECT t FROM %s t LIMIT :skip, :_limit", className);

        return em.createNamedQuery(query, clazz)
                .setParameter("skip", skip)
                .setParameter("_limit", limit)
                .getResultList();
    }

    public T getById(int id) {
        String query = String.format("SELECT t FROM %s t WHERE t.id = :id", className);
        return em.createQuery(query, clazz)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void delete(int id) {
        String query = String.format("DELETE t FROM %s t WHERE t.id = :id", className);
        em.createQuery(query, clazz)
                .setParameter("id", id)
                .executeUpdate();
    }

    public List<Device> getUserDevices(int accountId) {
        return em.createNamedQuery("User.getDevices", Device.class)
                .setParameter("userId", accountId)
                .getResultList();
    }

    public void update(T t) {
        em.refresh(t);
    }

    public void create(T t) {
        em.persist(t);
    }
}
