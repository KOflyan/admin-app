package com.admin.adminapi.base.service;

import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.base.dao.entities.AbstractEntity;
import com.admin.adminapi.base.dto.Dto;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.NoResultException;
import java.util.Set;

public abstract class GenericService<T extends AbstractEntity> {

    protected final Dao<T> dao;

    @Autowired
    public GenericService(Dao<T> dao) {
        this.dao = dao;
    }

    public T find(Long id)  {
        return dao.find(id);
    }

    public Set<T> findAll(int skip, int limit) {
        return dao.findAll(skip, limit);
    }

    public Set<T> findAll() {
        return dao.findAll();
    }

    public void delete(Long id) throws NoResultException {
        dao.delete(id);
    }

    public void save(Dto<T> dto) {
        dao.save(dto.get());
    }

    public Long count() {
        return dao.count();
    }

    public Set<T> search(String searchText) {
        return dao.search("%" + searchText + "%");
    }
}
