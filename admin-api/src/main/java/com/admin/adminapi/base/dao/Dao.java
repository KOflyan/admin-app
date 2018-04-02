package com.admin.adminapi.base.dao;

import com.admin.adminapi.utils.Utils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Component
@Transactional
public abstract class Dao<T extends Serializable> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> clazz;
    private String className;

    public Dao() {
        clazz = Utils.resolveClassOfT(getClass(), Dao.class);
        className = Utils.getClassName(clazz);
    }

    public List<T> findAll() {
        String query = String.format("SELECT t FROM %s t", className);

        return em.createQuery(query, clazz)
                .getResultList();
    }

    public List<T> findAll(int skip, int limit) {

        String query = String.format("SELECT t FROM %s t", className);

        return em.createQuery(query, clazz)
                .setFirstResult(skip)
                .setMaxResults(limit)
                .getResultList();
    }

    public T find(Long id) {
        return em.find(clazz, id);
    }

    public void delete(Long id) throws EntityNotFoundException {
        String query = String.format("DELETE FROM %s t WHERE t.id = :id", className);

        try {
            find(id);
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        }

        em.createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }

    public void create(T t) {
        em.persist(t);
    }

    public void update(Long id) throws EntityNotFoundException {
        String query = String.format("UPDATE %s t WHERE t.id = :id", className);

        try {
            find(id);
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        }

        em.createQuery(query, clazz)
                .setParameter("id", id)
                .executeUpdate();
    }
}
