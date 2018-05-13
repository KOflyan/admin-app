package com.admin.adminapi.base.dao;

import com.admin.adminapi.base.dao.entities.AbstractEntity;
import com.admin.adminapi.utils.Utils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
@Transactional
public abstract class Dao<T extends AbstractEntity> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> clazz;
    private String className;

    public Dao() {
        clazz = Utils.resolveClassOfT(getClass(), Dao.class);
        className = Utils.getClassName(clazz);
    }

    public Set<T> findAll() {
        String query = String.format("SELECT t FROM %s t", className);
        return new LinkedHashSet<>(em.createQuery(query, clazz)
                .getResultList());
    }

    public Set<T> findAll(int skip, int limit) {

        String query = String.format("SELECT t FROM %s t", className);

        return new LinkedHashSet<>(em.createQuery(query, clazz)
                .setFirstResult(skip)
                .setMaxResults(limit)
                .getResultList());
    }

    public T find(Long id) {
        return em.find(clazz, id);
    }

    public void delete(Long id) throws NoResultException {
        String query = String.format("DELETE FROM %s t WHERE t.id = :id", className);

        if (find(id) == null) {
            throw new NoResultException();
        }

        em.createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }

    public void save(T t) {
        try {
            em.merge(t);
        } catch (EntityNotFoundException ex) {
            em.persist(t);
        }
    }

    public Long count() {
        String query = String.format("SELECT COUNT(t) FROM %s t", className);
        return em.createQuery(query, Long.class)
                .getSingleResult();
    }

    public abstract Set<T> search(String searchText);
}
