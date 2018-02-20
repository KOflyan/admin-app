package com.adminapp.dao.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class Dao<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    public abstract List<T> getAll();

    public abstract T getById(int id);

    public abstract void delete(int id);

    public abstract void update(T t);

    public abstract void add(T t);
}
