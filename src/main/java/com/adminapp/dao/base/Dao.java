package com.adminapp.dao.base;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class Dao<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    public Dao() {

    }

    public abstract List<T> getAll();

    public abstract T getById();

    public abstract void delete(int id);

    public abstract void update(T t);

}
