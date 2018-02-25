package com.admin.adminapi.service.base;

import com.admin.adminapi.dao.base.Dao;
import com.admin.adminapi.dto.base.Dto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AdminApiService<T> {

    @Autowired
    private Dao<T> dao;

    public T getById(int id) {
        return dao.getById(id);
    }

    public List<T> getAll() {
        return dao.getAll();
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public void create(Dto<T> dto) {
        dao.create(dto.get());
    }

    public void update(Dto<T> dto) {
        dao.update(dto.get());
    }
}
