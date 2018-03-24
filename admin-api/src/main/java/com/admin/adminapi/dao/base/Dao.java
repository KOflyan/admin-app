package com.admin.adminapi.dao.base;

import com.admin.adminapi.entity.Account;
import com.admin.adminapi.entity.Device;
import com.admin.adminapi.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        return em.createQuery("SELECT t FROM " + className +" t", clazz)
                .getResultList();
    }

    public List<T> getAll(int skip, int limit) {

        String q = "SELECT *" +
                    "FROM :class `t`" +
                    "LIMIT :skip, :_limit";

        return em.createNamedQuery(q, clazz)
                .setParameter("class", className)
                .setParameter("skip", skip)
                .setParameter("limit", limit)
                .getResultList();
    }

    public T getById(int id) {
        String query = String.format("SELECT t FROM %s t WHERE t.id= :id", className);
        return em.createQuery(query, clazz)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void delete(int id) {
        String query = String.format("DELETE t FROM %s t WHERE t.id= :id", className);
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
