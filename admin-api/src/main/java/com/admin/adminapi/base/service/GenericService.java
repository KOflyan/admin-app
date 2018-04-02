package com.admin.adminapi.base.service;

import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.base.dao.entities.AbstractEntity;
import com.admin.adminapi.base.dto.Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.util.List;

@Component
public abstract class GenericService<T extends AbstractEntity> {

    @Autowired
    protected Dao<T> dao;

    public T find(Long id) throws NoResultException {
        return dao.find(id);
    }

    public List<T> findAll(int skip, int limit) {
        return dao.findAll(skip, limit);
    }

    public List<T> findAll() {
        return dao.findAll();
    }

    public void delete(Long id) throws NoResultException {
        dao.delete(id);
    }

    public void save(Dto<T> dto) {
        dao.save(dto.get());
    }
}
